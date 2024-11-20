package model.effects

import exceptions.Require.Stat
import model.entities.IEntity

/** Ateuluz
 *
 * This intermediate abstract class could be avoided, but remains
 * for the sole purpose of limiting the minimum allowed power.
 *
 * @param dmg The damage taken before each turn
 * @param duration The number of turns the effect lasts
 */
class ADamageEffect(
                     dmg: Int,
                     duration: Int
                   ) extends AEffect(dmg, duration) {
  Stat(dmg, "Damage") atLeast 1

  /** Ateuluz
   *
   * New condition to additionally deal damage.
   *
   * @param ent The entity to which the effect is applied to
   */
  override def applyTo(ent: IEntity): Unit = {
    ent.defendFromEffect(this)
    super.applyTo(ent)
  }
}
