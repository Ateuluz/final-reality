package model.entities.enemies

import model.entities.AEntity

abstract class AEnemy (
                        name: String,
                        hp: Int,
                        defense: Int,
                        attack: Int,
                        weight: Int
                      ) extends AEntity(name,hp,defense,weight)
                          with IEnemy {
  private val _attack: Int = constrainAttack(attack)
  def getAttack: Int = _attack
  def constrainAttack(attack: Int): Int = {
    attack match {
      case n if n <= 0 => 1
      case _ => attack
    }
  }
}
