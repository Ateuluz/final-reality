package model.entities.enemies

import exceptions.Require
import model.entities.{AEntity, IEntity}

/** Ateuluz
 *
 * @param name    The name of the entity
 * @param hp      The health points
 * @param defense The resistance to damage
 * @param attack  The attack power
 * @param weight  The weight of the entity
 */
abstract class AEnemy(
                       name: String,
                       hp: Int,
                       defense: Int,
                       attack: Int,
                       weight: Int
                     ) extends AEntity(name, hp, defense, weight)
  with IEnemy {
  private val _attack: Int = constrainAttack(attack)
  Require.Stat(attack, "Attack") atLeast 1

  /** Ateuluz
   *
   * @return The attack stat of this entity
   */
  override def getAttack: Int = _attack

  /** Ateuluz
   *
   * @param objective is the one to attack
   * @return the damage dealt, should we want to use it
   */
  override def attack(objective: IEntity): Int =
    objective.defendFromEnemy(getAttack)

  /** Ateuluz
   *
   * @param attack The original attack value
   * @return The final valid attack value
   */
  private def constrainAttack(attack: Int): Int = {
    if (attack < 1) 1
    else attack
  }

  /** Ateuluz
   *
   * @param attack The incoming attack value of a character
   * @return The damage that got past the defenders defense
   */
  override def defendFromCharacter(attack: Int): Int = defend(attack)

//  /** Ateuluz
//   *
//   * @param attack The incoming damage of a spell
//   * @return The damage that got past the defense
//   */
//  override def defendFromSpell(attack: Int): Int = defend(attack + getDefense / 2)

  /** Ateuluz
   *
   * @return the weight relevant for turn taking
   */
  override def getRelevantWeight: Int = getWeight
}
