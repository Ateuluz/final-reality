package model.entities.enemies

import model.entities.IEntity

/**
 * Trait representing an enemy and its methods
 */
trait IEnemy extends IEntity {
  /**
   *
   * @return The attack stat of this entity
   */
  def getAttack: Int
}
