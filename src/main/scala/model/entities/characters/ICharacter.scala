package model.entities.characters

import model.armament.IWeapon
import model.entities.IEntity

trait ICharacter extends IEntity {
  def getWeapon: Option[IWeapon]
  def unEquip(): Unit
  def equip(wp: IWeapon): Unit
}
