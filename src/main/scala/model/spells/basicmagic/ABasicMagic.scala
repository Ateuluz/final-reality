package model.spells.basicmagic

import model.entities.IEntity
import model.entities.characters.blackmage.BlackMage
import model.entities.characters.whitemage.WhiteMage
import model.spells.ASpell

abstract class ABasicMagic extends ASpell with IBasicMagic {

  /** Ateuluz
   *
   * @param caster The caster of the spell
   * @param target The target of the spell
   * @return The effect value of the spell, instant damage in most cases, hp healed in some, etc.
   */
  override def castByWhiteMage(caster: WhiteMage, target: IEntity): Int =
    cast(caster, target)

  /** Ateuluz
   *
   * @param caster The caster of the spell
   * @param target The target of the spell
   * @return The effect value of the spell, instant damage in most cases, hp healed in some, etc.
   */
  override def castByBlackMage(caster: BlackMage, target: IEntity): Int =
    cast(caster, target)
}
