package model.entities.characters

import model.armament.IWeapon
import model.entities.{AEntity, IEntity}

/** Define the basic traits of a character
 *
 * @param name    The name of the character
 * @param hp      The health points
 * @param defense The resistance to damage
 * @param weight  The weight of the character
 */
abstract class ACharacter(
                           name: String,
                           hp: Int,
                           defense: Int,
                           weight: Int
                        ) extends AEntity(name, hp, defense, weight)
                            with ICharacter {
  private var _weapon: Option[IWeapon] = None
  override def getWeapon: Option[IWeapon] = _weapon
  override def unEquip(): Unit = {
    _weapon match {
      case Some(wp) =>
        wp.unsetOwner()
        _weapon = None
      case None =>
    }
  }
  override def equip(wp: IWeapon): Unit = {
    wp match {
      case w if w.canBeEquippedBy(this) =>
        w.setOwner(this)
        this.unEquip()
        _weapon = Some(w)
      case _ =>
    }
  }

  override def attack(objective: IEntity): Int = {
    val atk = _weapon match {
      case Some(weapon) => weapon.getAttack
      case None =>
        println(s"${this.getClass} $name has no weapon equipped!")
        0
    }
    objective.defend(atk)
  }
}
