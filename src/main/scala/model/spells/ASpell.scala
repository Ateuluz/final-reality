package model.spells
import exceptions.InvalidCasterException
import model.entities.IEntity
import model.entities.characters.blackMage.BlackMage
import model.entities.characters.whiteMage.WhiteMage

abstract class ASpell extends ISpell {

  /** Ateuluz
   *
   * @param caster The caster of the spell
   * @param target The target of the spell
   *  @return The effect value of the spell, instant damage in most cases, hp healed in some, etc.
   */
  override def castByBlackMage(caster: BlackMage, target: IEntity): Int = {
    throw new InvalidCasterException("Spell cannot be cast by a black mage")
  }

  /** Ateuluz
   *
   * @param caster The caster of the spell
   * @param target The target of the spell
   *  @return The effect value of the spell, instant damage in most cases, hp healed in some, etc.
   */
  override def castByWhiteMage(caster: WhiteMage, target: IEntity): Int = {
    throw new InvalidCasterException("Spell cannot be cast by a white mage")
  }

}
