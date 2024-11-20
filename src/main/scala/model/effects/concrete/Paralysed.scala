package model.effects.concrete

import model.effects.AEffect
import model.entities.IEntity

/** Ateuluz
 *
 * Null damage effect that prevents an entity from taking a turn once.
 */
class Paralysed extends AEffect(0, 1) {

  /** Ateuluz
   *
   * New condition to unable the entity
   *
   * @param ent The entity to which the effect is applied to
   */
  override def applyTo(ent: IEntity): Unit = {
    ent.actionAble = false
    super.applyTo(ent)
  }
}
