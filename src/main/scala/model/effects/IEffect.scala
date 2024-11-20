package model.effects

import model.entities.IEntity

/** Ateuluz
 *
 * An interface defining all effect methods
 */
trait IEffect {

  /** Ateuluz
   *
   * Getter for dmg
   *
   * @return The dmg of this particular effect
   */
  def power: Int

  // TODO Check if any use
//  /** Ateuluz
//   *
//   * Getter for dmg
//   *
//   * @return The dmg of this particular effect
//   */
//  protected def duration: Int

  /** Ateuluz
   *
   * By invariant we know if it ever gets to
   * zero it will be removed, so no need for
   * a check
   */
  protected def reduce(): Unit

  /** Ateuluz
   *
   * Check if the effect is done.
   * If so, remove it.
   *
   * @param ent The entity we might remove the effect from
   */
  protected def checkRemoveFrom(ent: IEntity): Unit

  /** Ateuluz
   *
   * @param ent The entity to which the effect is applied to
   */
  def applyTo(ent: IEntity): Unit
}
