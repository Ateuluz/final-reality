package model.entities.enemies

import exceptions.Require
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
  Require.Stat(attack, "Attack") atLeast 1
  override def getAttack: Int = _attack
  override def attack(objective: IEntity): Int =
    objective.defend(_attack)
  /**
   *
   * @param attack The original attack value
   * @return The final valid attack value
   */
  private def constrainAttack(attack: Int): Int = {
    attack match {
      case n if n < 1 => 1
      case _ => attack
    }
  }
}
