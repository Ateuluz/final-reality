package model.spells.basicMagic.exSpell1

import exceptions.Require
import model.entities.IEntity
import model.entities.characters.IMagicalCharacter
import model.spells.basicMagic.ABasicMagic

/**
 * Just a spell for testing
 *
 * Serves as fireball
 */
class ExSpell1(
                spellAttack: Int,
                manaCost: Int
              ) extends ABasicMagic {

  private val _spellAttack: Int =
    Require.Stat(spellAttack, "Spell Attack") atLeast 1

  /**
   *
   * @return The mana points required and to be consumed
   *         by the spell
   */
  override def getConsumption: Int =
    Require.Stat(manaCost, "Mana Cost") atLeast 0

  /**
   *
   * @param caster The caster of the spell
   * @param target The target of the spell
   * @return The effect value of the spell, instant damage in most cases, hp healed in some, etc.
   */
  override protected def cast(caster: IMagicalCharacter, target: IEntity): Int = {
    target.defendFromSpell(_spellAttack + caster.getWeapon.get.getMagicAttack)
  }
}
