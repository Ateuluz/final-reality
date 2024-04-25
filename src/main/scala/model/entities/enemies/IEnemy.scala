package model.entities.enemies

import model.entities.IEntity

trait IEnemy extends IEntity {
  def getAttack: Int
  protected def constrainAttack(attack: Int): Int
}
