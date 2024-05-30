package model.entities.playablecharacters

import exceptions.InvalidActionException
import model.armament.IWeapon
import model.entities.{AEntity, IEntity}

/** Ateuluz Define the basic traits of a character
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

  /** Ateuluz
   *
   * @return The weapon the character holds, if any
   */
  override def getWeapon: Option[IWeapon] = _weapon

  /** Ateuluz
   *
   * @param weapon The weapon to equip
   */
  override protected def setWeapon(weapon: IWeapon): Unit = _weapon = Some(weapon)

  /** Ateuluz
   * We un equip the weapon off the character should it hold one
   */
  override def unEquip(): Unit = {
    if (this._weapon.isDefined) {
      _weapon.get.unsetOwner()
      _weapon = None
    }
  }

  /** Ateuluz
   *
   * @param objective is the one to attack
   * @return the damage dealt, should we want to use it
   */
  override def attack(objective: IEntity): Int =
    objective.defendFromCharacter(getAttack)

  /** Ateuluz
   *
   * @return The attack value of the entity, however it may be gotten
   */
  override def getAttack: Int = {
    if (_weapon.isDefined) _weapon.get.getAttack
    else throw new InvalidActionException("Cannot get attack of Character without Weapon.")
  }

  /** Ateuluz
   *
   * @param attack The incoming attack value of an enemy
   * @return The damage that got past the defenders defense
   */
  override def defendFromEnemy(attack: Int): Int = defend(attack)

  /** Ateuluz
   *
   * @param attack The incoming damage of a spell
   * @return The damage that got past the defense
   */
  override def defendFromSpell(attack: Int): Int = defend(attack + getDefense / 2)

  /** Ateuluz
   *
   * @return the weight relevant for turn taking
   */
  override def getRelevantWeight: Int = {
    if (getWeapon.isEmpty)
      throw new InvalidActionException("Character has no weapon, thus weight cannot be assigned")
    getWeight + getWeapon.get.getWeight / 2
  }
}
