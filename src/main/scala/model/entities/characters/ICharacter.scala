package model.entities.characters

import model.armament.IWeapon
import model.entities.IEntity

trait ICharacter extends IEntity {
  def getWeapon: Option[IWeapon]
  protected[model] def setWeapon(wp: IWeapon): Unit
  def unsetWeapon(): Unit
  def requestBindWeapon(wp: IWeapon): Unit
}
