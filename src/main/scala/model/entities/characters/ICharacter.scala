package model.entities.characters

import model.armament.IWeapon
import model.entities.IEntity

/**
 * Trait representing a character and its methods
 */
trait ICharacter extends IEntity {
  /**
   *
   * @return The weapon the character holds, if any
   */
  def getWeapon: Option[IWeapon]

  /**
   * We un equip the weapon off the character should it hold one
   */
  def unEquip(): Unit

  /**
   *
   * @param wp The weapon to equip
   */
  def equip(wp: IWeapon): Unit
}
