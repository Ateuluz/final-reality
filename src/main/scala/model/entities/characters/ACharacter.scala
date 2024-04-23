package model.entities.characters

import model.armament.IWeapon
import model.entities.AEntity

/** Define the basic traits of a character
 *
 * @param name    The name of the character
 * @param hp      The health points
 * @param defense The resistance to damage
 * @param weight  The weight of the character
 */
abstract class ACharacter(
                           name: String,
                           hp: Int,
                           defense: Int,
                           weight: Int
                        ) extends AEntity(name, hp, defense, weight)
                            with ICharacter {
  private var _weapon: Option[IWeapon] = None
  override def getWeapon: Option[IWeapon] = _weapon
  override def setWeapon(wp: IWeapon): Unit = {
    this.unsetWeapon()
    _weapon = Some(wp)
  }
  override def unsetWeapon(): Unit = {
    _weapon match {
      case Some(wp) =>
        wp.unsetOwner()
        _weapon = None
      case None =>
    }
  }
  override def requestBindWeapon(wp: IWeapon): Unit = {
    wp match {
      case w if w.getOwner.isEmpty => w.setOwner(this)
      case _ =>
    }
  }
}
