package model.entities.enemies

import model.entities.{AEntity, IEntity}

abstract class AEnemy (
                        name: String,
                        hp: Int,
                        defense: Int,
                        attack: Int,
                        weight: Int
                      ) extends AEntity(name,hp,defense,weight)
                          with IEnemy {
  private val _attack: Int = constrainAttack(attack)
  override def getAttack: Int = _attack
  override def constrainAttack(attack: Int): Int = {
    attack match {
      case n if n <= 0 => 1
      case _ => attack
    }
  }
  override def attack(objective: IEntity): Int =
    objective.defend(_attack)
}
