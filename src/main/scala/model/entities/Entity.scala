package model.entities

trait Entity {
  def getName: String
  def getHp: Int
  def getDefense: Int
  def getWeight: Int
}
