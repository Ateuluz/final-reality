package model.entities

abstract class AEntity (
                         name:String,
                         hp:Int,
                         defense:Int,
                         weight:Int
                       ) extends IEntity {
  private val _name: String = name
  private var _hp: Int = constrainHp(hp)
  private val _defense: Int = constrainDefense(defense)
  private val _weight: Int = constrainWeight(weight)

  override def getName: String = {
    _name
  }
  override def getHp: Int = {
    _hp
  }
  override def setHp(hp: Int): Unit = {
    _hp = constrainHp(hp)
  }
  override def constrainHp(hp: Int): Int = {
    hp match {
      case n if n < 0 => 0
      case _ => hp
    }
  }
  override def getDefense: Int = {
    _defense
  }
  override def constrainDefense(defense: Int): Int = {
    defense match {
      case n if n < 0 => 0
      case _ => defense
    }
  }
  override def getWeight: Int = {
    _weight
  }
  override def constrainWeight(weight: Int): Int = {
    weight match {
      case n if n <= 0 => 1
      case _ => weight
    }
  }
}