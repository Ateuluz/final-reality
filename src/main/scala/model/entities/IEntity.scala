package model.entities

trait IEntity {
  def getName: String
  def getHp: Int
  def setHp(hp: Int): Unit
  def getDefense: Int
  def getWeight: Int
  def attack(objective: IEntity): Int
  def defend(attack: Int): Int
}
