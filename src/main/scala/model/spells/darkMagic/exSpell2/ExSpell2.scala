package model.spells.darkMagic.exSpell2

import exceptions.Require
import model.entities.IEntity
import model.entities.characters.IMagicalCharacter
import model.spells.darkMagic.ADarkMagic

/**
 * Just a spell for testing
 *
 * Serves as black magic
 */
class ExSpell2(
                spellAttack: Int,
                manaCost: Int
              ) extends ADarkMagic {

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
   * Damage the target by a constant amount
   * The caster loses a third of the attack
   * as hp and recovers half of the damage
   *
   * If the caster dies in the process,
   * the spell gets canceled
   *
   * @param caster The caster of the spell
   * @param target The target of the spell
   * @return The effect value of the spell, instant damage in most cases, hp healed in some, etc.
   */
  override protected def cast(caster: IMagicalCharacter, target: IEntity): Int = {
    caster.defendFromSpell(_spellAttack / 3 + caster.getDefense)
    var damage = 0
    if (caster.getHp > 0) damage = {
      target.defendFromSpell(_spellAttack)
      caster.beHealed(damage / 2)
    }
    damage
  }
}
