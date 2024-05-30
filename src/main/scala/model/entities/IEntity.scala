package model.entities

/** Ateuluz
 * Trait representing an entity and its methods
 */
trait IEntity {
  /** Ateuluz
   *
   * @return The name of the entity
   */
  def getName: String

  /** Ateuluz
   *
   * @return The hp of the entity
   */
  def getHp: Int

  /** Ateuluz
   *
   * @return The max possible hp of the entity
   */
  def getHpMax: Int

  /** Ateuluz
   *
   * @return The defense of the entity
   */
  def getDefense: Int

  /** Ateuluz
   *
   * @return The weight of the entity
   */
  def getWeight: Int

  /** Ateuluz
   *
   * @param objective is the one to attack
   * @return the damage dealt, should we want to use it
   */
  def attack(objective: IEntity): Int

  /** Ateuluz
   * Every entity partakes in a battle, so they need an attack value.
   *
   * @return The attack value of the entity, however it may be gotten
   */
  def getAttack: Int

  /** Ateuluz
   *
   * @param attack The incoming attack value
   * @return The damage that got past the defenders defense
   */
  protected def defend(attack: Int): Int

  /** Ateuluz
   *
   * @param attack The incoming attack value of a character
   * @return The damage that got past the defenders defense
   */
  def defendFromCharacter(attack: Int): Int

  /** Ateuluz
   *
   * @param attack The incoming attack value of an enemy
   * @return The damage that got past the defenders defense
   */
  def defendFromEnemy(attack: Int): Int

  /** Ateuluz
   *
   * @param attack The incoming damage of a spell
   * @return The damage that got past the defense
   */
  def defendFromSpell(attack: Int): Int

  /** Ateuluz
   *
   * @param hpHealValue The hp to recover by the entity
   * @return Final heal amount
   */
  def beHealed(hpHealValue: Int): Int
}
