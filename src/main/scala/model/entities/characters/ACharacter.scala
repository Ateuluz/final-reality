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

  /**
   *
   *  @return The weapon the character holds, if any
   */
  override def getWeapon: Option[IWeapon] = _weapon

  /**
   * We un equip the weapon off the character should it hold one
   */
  override def unEquip(): Unit = {
    _weapon match {
      case Some(wp) =>
        wp.unsetOwner()
        _weapon = None
      case None =>
    }
  }

  /**
   *
   * @param objective is the one to attack
   *  @return the damage dealt, should we want to use it
   */
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
