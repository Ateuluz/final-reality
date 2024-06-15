package model.spells.basicmagic.concrete

import exceptions.Require
import model.entities.IEntity
import model.entities.playablecharacters.IMagicalCharacter
import model.spells.basicmagic.ABasicMagic
import model.effects.concrete.Burned

/** Ateuluz
 * Just a spell for testing
 *
 * Serves as fireball
 */
class Fireball(
                spellAttack: Int,
                manaCost: Int
              ) extends ABasicMagic {

  private val _spellAttack: Int =
    Require.Stat(spellAttack, "Spell Attack") atLeast 1

  /** Ateuluz
   *
   * @return The mana points required and to be consumed
   *         by the spell
   */
  override def getConsumption: Int =
    Require.Stat(manaCost, "Mana Cost") atLeast 0

  /** Ateuluz
   *
   * @param caster The caster of the spell
   * @param target The target of the spell
   * @return The effect value of the spell, instant damage in most cases, hp healed in some, etc.
   */
  override protected def cast(caster: IMagicalCharacter, target: IEntity): Int = {
    val dmg = target.defendFromSpell(_spellAttack + caster.getWeapon.get.getMagicAttack)
    target.effectsAdd(new Burned(caster.getMagicAttack/2))
    dmg
  }
}
