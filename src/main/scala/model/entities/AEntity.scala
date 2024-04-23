package model.entities

abstract class AEntity (
                       private val name: String,
                       private var hp: Int,
                       private val defense: Int,
                       private val weight: Int,
                       ) extends Entity {

  protected val foo: String

  def getName: String = {
    this.name
  }
  def getHp: Int = {
    this.hp
  }
  def getDefense: Int = {
    this.defense
  }
  def getWeight: Int = {
    this.weight
  }

}
