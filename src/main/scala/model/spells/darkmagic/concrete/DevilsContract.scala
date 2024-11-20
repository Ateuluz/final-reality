package model.spells.darkmagic.concrete

import exceptions.Require
import model.effects.concrete.{Paralysed, Poisoned}
import model.entities.IEntity
import model.entities.playablecharacters.IMagicalCharacter
import model.spells.darkmagic.ADarkMagic

/** Ateuluz
 * Just a spell for testing
 *
 * Serves as black magic
 */
class DevilsContract(
                spellAttack: Int,
                manaCost: Int
              ) extends ADarkMagic {

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
   * TODO check for spell's profitability
   *
   * Damage the target by a constant amount
   * The caster loses a third of the attack
   * as hp and recovers half of the damage
   *
   * As a bonus, apply Paralysis and Poison on the target.
   * Poison by itself is weaker than Burned, so this will
   * make a fun twist.
   *
   * If the caster dies in the process,
   * the spell gets canceled
   *
   * @param caster The caster of the spell
   * @param target The target of the spell
   * @return The effect value of the spell, instant damage in most cases, hp healed in some, etc.
   */
  override protected def cast(caster: IMagicalCharacter, target: IEntity): Int = {
    caster.defendFromSpell(_spellAttack / 3)
    var damage = 0
    if (caster.getHp > 0) {
      damage = target.defendFromSpell(_spellAttack)
      target.effectsAdd(new Paralysed())
      target.effectsAdd(new Poisoned(caster.getMagicAttack / 3))
      caster.beHealed(2 * damage / 3)
    }
    damage
  }
}
