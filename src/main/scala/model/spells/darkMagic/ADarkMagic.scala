package model.spells.darkMagic

import model.entities.IEntity
import model.entities.characters.blackMage.BlackMage
import model.spells.ASpell

abstract class ADarkMagic extends ASpell with IDarkMagic {
  override def castByBlackMage(caster: BlackMage, target: IEntity): Int =
    cast(caster, target)
}
