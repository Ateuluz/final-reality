package model.effects
import exceptions.Require.Stat
import model.entities.IEntity

/** Ateuluz
 *
 * An abstract that encapsulates all effects
 *
 * In most cases power equates damage.
 *
 * @param powerToSet Value to measure Effect's effect before each turn
 * @param durationToSet The number of turns the effect lasts
 */
abstract class AEffect (
                         powerToSet: Int,
                         durationToSet: Int
                       ) extends IEffect {

  private val _power: Int = Stat (powerToSet, "Power") atLeast 0 // Considering zero for implementations such as paralysis
  private var _duration: Int = Stat (durationToSet, "Duration") atLeast 1

  /** Ateuluz
   *
   * Getter for dmg
   *
   * @return The dmg of this particular effect
   */
  override def power    : Int   = _power

//  /** Ateuluz
//   *
//   * Getter for dmg
//   * Protected since it's handled by the effect itself
//   *
//   * @return The dmg of this particular effect
//   */
//  override protected def duration : Int   = _duration

  /** Ateuluz
   *
   * By invariant we know if it ever gets to
   * zero it will be removed, so no need for
   * a check
   */
  override protected def reduce() : Unit  =
    _duration -= 1

  /** Ateuluz
   *
   * Its only purpose is to reduce its duration.
   *
   * @param ent The entity to which the effect is applied to
   */
  override def applyTo(ent: IEntity): Unit = {
    //ent.defendFromEffect(this)
    reduce()
    checkRemoveFrom(ent)
  }

  /** Ateuluz
   *
   * Check if the effect is done.
   * If so, remove it.
   *
   * @param ent The entity we might remove the effect from
   */
  override protected def checkRemoveFrom(ent: IEntity): Unit =
    if (_duration == 0) ent.effectsRemove(this)

}
