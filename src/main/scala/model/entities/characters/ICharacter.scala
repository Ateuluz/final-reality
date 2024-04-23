package model.entities.characters

import model.armament.IWeapon

trait ICharacter {
  def getWeapon: Option[IWeapon]
  def setWeapon(wp: IWeapon): Unit
  def unsetWeapon(): Unit
  def requestBindWeapon(wp: IWeapon): Unit
}
