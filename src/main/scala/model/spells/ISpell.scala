package model.spells

import model.entities.IEntity
import model.entities.characters.IMagicalCharacter
import model.entities.characters.blackMage.BlackMage
import model.entities.characters.whiteMage.WhiteMage

/**
 * A Trait defining methods for a spell
 */
trait ISpell {

  /**
   *
   * @return The mana points required and to be consumed
   *         by the spell
   */
  def getConsumption: Int

  /**
   *
   * @param caster The caster of the spell
   * @param target The target of the spell
   * @return The effect value of the spell, instant damage in most cases, hp healed in some, etc.
   */
  def castByBlackMage(caster: BlackMage, target: IEntity): Int

  /**
   *
   * @param caster The caster of the spell
   * @param target The target of the spell
   * @return The effect value of the spell, instant damage in most cases, hp healed in some, etc.
   */
  def castByWhiteMage(caster: WhiteMage, target: IEntity): Int

  /**
   *
   * Defined to simplify spell casting methods.
   *
   * @param caster The caster of the spell
   * @param target The target of the spell
   * @return The effect value of the spell, instant damage in most cases, hp healed in some, etc.
   */
  protected def cast(caster: IMagicalCharacter, target: IEntity): Int
}
