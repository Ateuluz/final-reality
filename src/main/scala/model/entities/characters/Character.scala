package model.entities.characters

import model.armament.Weapon

trait Character {
  def setWeapon(wp: Weapon): Unit
  def getWeapon: Weapon
}
