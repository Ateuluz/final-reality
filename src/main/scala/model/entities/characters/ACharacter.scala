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
   *
   * @param weapon The weapon to equip
   */
  override protected def setWeapon(weapon: IWeapon): Unit = _weapon = Some(weapon)

  /**
   * We un equip the weapon off the character should it hold one
   */
  override def unEquip(): Unit = {
    if (this._weapon.isDefined) {
      _weapon.get.unsetOwner()
      _weapon = None
    }
  }

  /**
   *
   * @param objective is the one to attack
   *  @return the damage dealt, should we want to use it
   */
  override def attack(objective: IEntity): Int = {
    if (_weapon.isDefined)
      objective.defend(_weapon.get.getAttack)
    else {
      println(s"${this.getClass} $name has no weapon equipped!")
      objective.defend(0)
    }
  }
}
