package model.entities

trait IEntity {
  def getName: String
  def getHp: Int
  def setHp(hp: Int): Unit
  protected def constrainHp(hp: Int): Int
  def getDefense: Int
  protected def constrainDefense(hp: Int): Int
  def getWeight: Int
  protected def constrainWeight(hp: Int): Int
  def attack(objective: IEntity): Int
  protected def defend(attack: Int): Int
  protected def constrainDamage(damage: Int): Int
}
