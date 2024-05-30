package model.entities.characters

import model.armament.IWeapon
import model.entities.IEntity

/** Ateuluz
 * Trait representing a character and its methods
 */
trait ICharacter extends IEntity {
  /** Ateuluz
   *
   * @return The weapon the character holds, if any
   */
  def getWeapon: Option[IWeapon]

  /** Ateuluz
   * We un equip the weapon off the character should it hold one
   */
  def unEquip(): Unit

  /** Ateuluz
   *
   * @param wp The weapon to equip
   */
  def equip(wp: IWeapon): Unit

  /** Ateuluz
   *
   * @param weapon The weapon to equip
   */
  protected def setWeapon(weapon: IWeapon): Unit
}
