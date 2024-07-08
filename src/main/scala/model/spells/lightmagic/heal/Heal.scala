package model.spells.lightmagic.heal

import exceptions.Require
import model.entities.IEntity
import model.entities.playablecharacters.IMagicalCharacter
import model.spells.lightmagic.ALightMagic
import model.spells.lightmagic.selectionstrategy.{AllyTargetSelectionStrategy, ITargetSelectionStrategy}

/** Ateuluz
 * Just a spell for testing
 *
 * Serves as heal
 */
class Heal (
             hpHealValue: Int,
             manaCost: Int
           ) extends ALightMagic {

  private val _hpHealValue: Int =
    Require.Stat(hpHealValue, "Heal Value") atLeast 1

  /** Ateuluz
   *
   *  @return The mana points required and to be consumed
   *         by the spell
   */
  override def getConsumption: Int =
    Require.Stat(manaCost, "Mana Cost") atLeast 0

  /** Ateuluz
   *
   * Heal the target by a constant amount
   *
   * @param caster The caster of the spell
   * @param target The target of the spell
   *  @return The effect value of the spell, instant damage in most cases, hp healed in some, etc.
   */
  override protected def cast(caster: IMagicalCharacter, target: IEntity): Int =
    target.beHealed(_hpHealValue)

  /** Ateuluz
   *
   *  @return A class that has a getTargets method
   */
  override def targetStrategy: ITargetSelectionStrategy = new AllyTargetSelectionStrategy
}
