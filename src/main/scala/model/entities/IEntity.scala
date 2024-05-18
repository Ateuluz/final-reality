package model.entities

/**
 * Trait representing an entity and its methods
 */
trait IEntity {
  /**
   *
   * @return The name of the entity
   */
  def getName: String

  /**
   *
   * @return The hp of the entity
   */
  def getHp: Int

  /**
   *
   * @return The defense of the entity
   */
  def getDefense: Int

  /**
   *
   * @return The weight of the entity
   */
  def getWeight: Int

  /**
   *
   * @param objective is the one to attack
   * @return the damage dealt, should we want to use it
   */
  def attack(objective: IEntity): Int

  /**
   * Every entity partakes in a battle, so they need an attack value.
   *
   * @return The attack value of the entity, however it may be gotten
   */
  def getAttack: Int

  /**
   *
   * @param attack The incoming attack value
   * @return The damage that got past the defenders defense
   */
  protected def defend(attack: Int): Int

  /**
   *
   * @param attack The incoming attack value of a character
   * @return The damage that got past the defenders defense
   */
  def defendFromCharacter(attack: Int): Int

  /**
   *
   * @param attack The incoming attack value of an enemy
   * @return The damage that got past the defenders defense
   */
  def defendFromEnemy(attack: Int): Int
}
