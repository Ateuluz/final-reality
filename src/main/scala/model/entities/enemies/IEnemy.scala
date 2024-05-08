package model.entities.enemies

import model.entities.IEntity

trait IEnemy extends IEntity {
  /**
   *
   * @return The attack stat of this entity
   */
  def getAttack: Int
}
