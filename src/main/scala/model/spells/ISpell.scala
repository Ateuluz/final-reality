package model.spells

import model.entities.IEntity
import model.entities.playablecharacters.IMagicalCharacter
import model.entities.playablecharacters.concrete.{BlackMage, WhiteMage}
import model.spells.lightmagic.selectionstrategy.ITargetSelectionStrategy

/** Ateuluz
 * An interface defining methods for a spell
 */
trait ISpell {

  /** Ateuluz
   *
   * @return The mana points required and to be consumed
   *         by the spell
   */
  def getConsumption: Int

  /** Ateuluz
   *
   * @param caster The caster of the spell
   * @param target The target of the spell
   * @return The effect value of the spell, instant damage in most cases, hp healed in some, etc.
   */
  def castByBlackMage(caster: BlackMage, target: IEntity): Int

  /** Ateuluz
   *
   * @param caster The caster of the spell
   * @param target The target of the spell
   * @return The effect value of the spell, instant damage in most cases, hp healed in some, etc.
   */
  def castByWhiteMage(caster: WhiteMage, target: IEntity): Int

  /** Ateuluz
   *
   * Defined to simplify spell casting methods.
   *
   * @param caster The caster of the spell
   * @param target The target of the spell
   * @return The effect value of the spell, instant damage in most cases, hp healed in some, etc.
   */
  protected def cast(caster: IMagicalCharacter, target: IEntity): Int

  /** Ateuluz
   *
   * Simplification for target getter
   * This is needed for spells that may target companions
   *
   * @return A class that has a getTargets method
   */
  def targetStrategy: ITargetSelectionStrategy
}
