package model.entities

trait Entity {
  def getName: String
  def getHp: Int
  def setHp(hp: Int): Unit
  def constrainHp(hp: Int): Int
  def getDefense: Int
  def constrainDefense(hp: Int): Int
  def getWeight: Int
  def constrainWeight(hp: Int): Int
}
