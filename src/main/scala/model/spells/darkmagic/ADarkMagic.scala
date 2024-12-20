package model.spells.darkmagic

import model.entities.IEntity
import model.entities.playablecharacters.concrete.BlackMage
import model.spells.ASpell

/** Ateuluz
 * An abstract class for all dark magic spells
 */
abstract class ADarkMagic extends ASpell with IDarkMagic {
  override def castByBlackMage(caster: BlackMage, target: IEntity): Int =
    cast(caster, target)
}
