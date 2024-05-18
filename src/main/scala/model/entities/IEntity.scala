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
   *
   * @param attack The incoming attack value
   * @return The damage that got past the defenders defense
   */
  def defend(attack: Int): Int
  def defendFromCharacter(attack: Int): Int
  def defendFromEnemy(attack: Int): Int
}
