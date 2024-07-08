package model.spells.lightmagic

import model.entities.IEntity
import model.entities.playablecharacters.whitemage.WhiteMage
import model.spells.ASpell

/** Ateuluz
 * A class for all light spells
 */
abstract class ALightMagic extends ASpell with ILightMagic {
  /** Ateuluz
   *
   * @param caster The caster of the spell
   * @param target The target of the spell
   *  @return The effect value of the spell, instant damage in most cases, hp healed in some, etc.
   */
  override def castByWhiteMage(caster: WhiteMage, target: IEntity): Int =
    cast(caster, target)
}
