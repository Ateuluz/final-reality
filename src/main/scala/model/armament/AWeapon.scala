package model.armament

import exceptions.Require
import model.entities.characters.ICharacter

abstract class AWeapon (
                         name: String,
                         attack: Int,
                         weight: Int
                       ) extends IWeapon {
  private val _name: String = name
  private val _attack: Int = constrainAttack(attack)
  private val _weight: Int = constrainWeight(weight)
  private var _owner: Option[ICharacter] = None
  Require.Stat(attack, "Attack") atLeast 1
  Require.Stat(weight, "Weight") atLeast 0

  override def getName: String = _name
  override def getAttack: Int = _attack
  override def getWeight: Int = _weight
  override def getOwner: Option[ICharacter] = _owner
  private def constrainAttack(attack: Int): Int =
    attack match {
      case n if n < 1 => 1
      case _ => attack
    }
  private def constrainWeight(weight: Int): Int =
    weight match {
      case n if n < 0 => 0
      case _ => weight
    }
  override protected[model] def setOwner(owner: ICharacter): Unit = {
    _owner match {
      case Some(char) => char.unsetWeapon()
      case _ =>
    }
    _owner = Some(owner)
    owner.setWeapon(this)
  }
  override def unsetOwner(): Unit = {
    _owner = None
  }
}
