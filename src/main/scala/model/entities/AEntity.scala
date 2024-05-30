package model.entities

import exceptions.Require.Stat
import exceptions.InvalidActionException

/** Ateuluz
 *
 * @param name    The name of the entity
 * @param hp      The health points
 * @param defense The resistance to damage
 * @param weight  The weight of the entity
 */
abstract class AEntity(
                        name: String,
                        hp: Int,
                        defense: Int,
                        weight: Int
                      ) extends IEntity {
  private val _name: String = name
  private var _hp: Int = constrainHp(hp)
  private var _hpMax: Int = _hp
  private val _defense: Int = constrainDefense(defense)
  private val _weight: Int = constrainWeight(weight)
  Stat(hp, "HP") atLeast 0
  Stat(defense, "Defense") atLeast 0
  Stat(weight, "Weight") atLeast 1

  /** Ateuluz
   *
   * @return The name of the entity
   */
  override def getName: String = _name

  /** Ateuluz
   *
   * @return The hp of the entity
   */
  override def getHp: Int = _hp

  /** Ateuluz
   *
   * @return The max possible hp of the entity
   */
  override def getHpMax: Int = _hpMax

  /** Ateuluz
   *
   * @return The defense of the entity
   */
  override def getDefense: Int = _defense

  /** Ateuluz
   *
   * @return The weight of the entity
   */
  override def getWeight: Int = _weight

  /** Ateuluz
   *
   * @param attack The incoming attack value
   * @return The damage that got past the defenders defense
   */
  override protected def defend(attack: Int): Int = {
    val dmg = constrainDamage(attack - _defense)
    _hp -= dmg
    dmg
  }

  /** Ateuluz
   *
   * @param hp The original hp value
   * @return The final valid hp value
   */
  private def constrainHp(hp: Int): Int = {
    hp match {
      case n if n < 0 => 0
      case _ => hp
    }
  }

  /** Ateuluz
   *
   * @param defense The original defense value
   * @return The final valid defense value
   */
  private def constrainDefense(defense: Int): Int = {
    defense match {
      case n if n < 0 => 0
      case _ => defense
    }
  }

  /** Ateuluz
   *
   * @param weight The original weight value
   * @return The final valid weight value
   */
  private def constrainWeight(weight: Int): Int = {
    weight match {
      case n if n < 1 => 1
      case _ => weight
    }
  }

  /** Ateuluz
   *
   * @param damage The intended attack damage
   * @return The real damage to be dealt
   */
  private def constrainDamage(damage: Int): Int =
    damage match {
      case dmg if dmg > _hp => _hp
      case dmg if dmg > 0 => dmg
      case _ => 0
    }

  /** Ateuluz
   *
   * @param attack The incoming attack value of a character
   * @return The damage that got past the defenders defense
   */
  override def defendFromCharacter(attack: Int): Int = {
    throw new InvalidActionException("Character cannot attack this entity.")
  }

  /** Ateuluz
   *
   * @param attack The incoming attack value of an enemy
   * @return The damage that got past the defenders defense
   */
  override def defendFromEnemy(attack: Int): Int = {
    throw new InvalidActionException("Enemy cannot attack this entity.")
  }

  //region To add if any other classes are needed
  /*
  /** Ateuluz
   *
   * @param attack The incoming damage of a spell
   *  @return The damage that got past the defense
   */
  override def defendFromSpell(attack: Int): Int = {
    throw new InvalidActionException("Spell cannot attack this entity.")
  }
  */
  //endregion

  /** Ateuluz
   *
   * @param hpHealValue The hp to recover by the entity
   * @return Final heal amount
   */
  override def beHealed(hpHealValue: Int): Int = {
    var dif = 0
    _hp + hpHealValue match {
      case _ if _hp == 0 => dif = 0
      case newHp if newHp > _hpMax => dif = _hpMax - _hp
      case newHp => dif = newHp - _hp
    }
    _hp += dif
    dif
  }

}