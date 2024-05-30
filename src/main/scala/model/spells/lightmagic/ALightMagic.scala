package model.spells.lightmagic

import model.entities.IEntity
import model.entities.playablecharacters.whitemage.WhiteMage
import model.spells.ASpell

/** Ateuluz
 * A class for all light spells
 */
abstract class ALightMagic extends ASpell with ILightMagic {
  override def castByWhiteMage(caster: WhiteMage, target: IEntity): Int =
    cast(caster, target)
}
