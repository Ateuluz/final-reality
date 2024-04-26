package model.entities

import exceptions.Require

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
  Require.Stat(hp, "HP") atLeast 0
  Require.Stat(defense, "Defense") atLeast 0
  Require.Stat(weight, "Weight") atLeast 1

  override def getName: String = {
    _name
  }
  override def getHp: Int = {
    _hp
  }
  private def constrainHp(hp: Int): Int = {
    hp match {
      case n if n < 0 => 0
      case _ => hp
    }
  }
  override def getDefense: Int = {
    _defense
  }
  private def constrainDefense(defense: Int): Int = {
    defense match {
      case n if n < 0 => 0
      case _ => defense
    }
  }
  override def getWeight: Int = {
    _weight
  }
  private def constrainWeight(weight: Int): Int = {
    weight match {
      case n if n < 1 => 1
      case _ => weight
    }
  }
  override def defend(attack: Int): Int = {
    val dmg = constrainDamage(attack - _defense)
    _hp -= dmg
    dmg
  }
  private def constrainDamage(damage: Int): Int =
    damage match {
      case dmg if dmg > _hp => _hp
      case dmg if dmg > 0 => dmg
      case _ => 0
  }
}