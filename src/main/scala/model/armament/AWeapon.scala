package model.armament

import exceptions.Require
import model.entities.characters.ICharacter

/**
 *
 * @param name The name for the weapon
 * @param attack The attack for the weapon
 * @param weight The weight for the weapon
 */
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

  /**
   *
   *  @return weapons name
   */
  override def getName: String = _name

  /**
   *
   *  @return weapons attack
   */
  override def getAttack: Int = _attack

  /**
   *
   *  @return weapons weight
   */
  override def getWeight: Int = _weight

  /**
   *
   *  @return weapons owner if any else None
   */
  override def getOwner: Option[ICharacter] = _owner

  /**
   *
   * @param attack The intended attack value
   * @return The final valid attack value
   */
  private def constrainAttack(attack: Int): Int =
    attack match {
      case n if n < 1 => 1
      case _ => attack
    }

  /**
   *
   * @param weight The intended weight value
   * @return The final valid weight value
   */
  private def constrainWeight(weight: Int): Int =
    weight match {
      case n if n < 0 => 0
      case _ => weight
    }

  /**
   *
   * @param owner The character to which the weapon will be equipped
   */
  override protected[model] def setOwner(owner: ICharacter): Unit = {
    /*_owner match {
      case Some(char) => char.unEquip()
      case _ =>
    }*/ // Case if owner override is possible
    _owner = Some(owner)
    /*owner.setWeapon(this)*/ // Already handled in characters
  }

  /**
   * Set the owner of the weapon back to NoneSet the owner of the weapon back to None
   */
  override protected[model] def unsetOwner(): Unit = {
    _owner = None
  }
}
