package model.entities.characters

import model.armament.IWeapon
import model.entities.IEntity

trait ICharacter extends IEntity {
  /**
   *
   * @return The weapon the character holds, if any
   */
  def getWeapon: Option[IWeapon]

  /**
   * We un equip the weapon off the character should he hold one
   */
  def unEquip(): Unit
  /**
   * We equip a weapon to the character, changing it if possessing another
   */
  def equip(wp: IWeapon): Unit
}
