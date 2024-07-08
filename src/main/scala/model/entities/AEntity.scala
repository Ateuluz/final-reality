package model.entities

import exceptions.Require.Stat
import exceptions.{InvalidActionException, InvalidHandleException}
import model.effects.IEffect

import scala.collection.mutable.ArrayBuffer

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
  private val _hpMax: Int = _hp
  private val _defense: Int = constrainDefense(defense)
  private val _weight: Int = constrainWeight(weight)
  Stat(hp, "HP") atLeast 0
  Stat(defense, "Defense") atLeast 0
  Stat(weight, "Weight") atLeast 1
  private val _effects = ArrayBuffer[IEffect]()
  private var _actionAble = false

  override def effects: ArrayBuffer[IEffect] = _effects.clone()

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
    if (hp < 0) 0
    else hp
  }

  /** Ateuluz
   *
   * @param defense The original defense value
   * @return The final valid defense value
   */
  private def constrainDefense(defense: Int): Int = {
    if (defense < 0) 0
    else defense
  }

  /** Ateuluz
   *
   * @param weight The original weight value
   * @return The final valid weight value
   */
  private def constrainWeight(weight: Int): Int = {
    if (weight < 1) 1
    else weight
  }

  /** Ateuluz
   *
   * @param damage The intended attack damage
   * @return The real damage to be dealt
   */
  private def constrainDamage(damage: Int): Int =
    if (damage > _hp)
      _hp
    else if (damage > 0)
      damage
    else
      0

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
  // All implementations are the same so far

  /** Ateuluz
   *
   * @param attack The incoming damage of a spell
   * @return The damage that got past the defense
   */
  override def defendFromSpell(attack: Int): Int = {
    defend(attack + getDefense / 2)
    // throw new InvalidActionException("Spell cannot attack this entity.")
  }

  //endregion

  /** Ateuluz
   *
   * @param hpHealValue The hp to recover by the entity
   * @return Final heal amount
   */
  override def beHealed(hpHealValue: Int): Int = {
    var dif = 0
    val tHP = _hp + hpHealValue
    if (_hp == 0)
      dif = 0
    else if (tHP > _hpMax)
      dif = _hpMax - _hp
    else
      dif = tHP - _hp
    _hp += dif
    dif
  }

  // TODO implement when needed
//  /** Ateuluz
//   *
//   * @return All effects the entity is under
//   */
//  override protected def effects: ArrayBuffer[IEffect] = _effects

  /** Ateuluz
   *
   * @param effect Effect to add
   */
  override def effectsAdd(effect: IEffect): Unit =
    _effects.addOne(effect)

  // TODO Will implement when needing to remove an effect before duration ends
  /** Ateuluz
   *
   * @param effect Effect to remove
   */
  override def effectsRemove(effect: IEffect): Unit = {
    if (!_effects.contains(effect)) throw new InvalidHandleException("Effect Not Found")
    _effects -= effect
  }

  /** Ateuluz
   *
   * @param effect Effect from where to get the true damage intended
   * @return The final damage
   */
  override def defendFromEffect(effect: IEffect): Int = {
    if (!_effects.contains(effect)) throw new InvalidHandleException("Effect Not Found")
    defend(effect.power + getDefense)
  }

  /** Ateuluz
   *
   * Apply all effects the entity is under
   */
  override def effectsApply(): Unit = {
    for (effect <- _effects.clone()) effect.applyTo(this)
//    val toRemove = ArrayBuffer[IEffect]()
//    for (effect <- _effects) if (effect.duration == 0) toRemove += effect
//    _effects --= toRemove
  }

  /** Ateuluz
   *
   * The check for hp may seem redundant, but becomes necessary
   * for when effects deal damage before each turn.
   *
   *  @return whether the entity can perform any action
   */
  override def actionAble: Boolean = _actionAble && (_hp != 0)

  /** Ateuluz
   *
   * Setter for _actionAble.
   * Won't matter if the entity is dead anyway.
   *
   * @param state Boolean for new actionAble state
   */
  override def actionAble_=(state: Boolean): Unit = {
    _actionAble = state
  }


}