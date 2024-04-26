package model.armament

import model.entities.characters.ICharacter

trait IWeapon {
  def getName: String
  def getAttack: Int
  def getWeight: Int
  def getOwner: Option[ICharacter]
  def setOwner(owner: ICharacter): Unit
  def unsetOwner(): Unit
}
