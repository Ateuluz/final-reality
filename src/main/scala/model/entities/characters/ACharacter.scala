package model.entities.characters

import model.armament.Weapon
import model.entities.{AEntity, Entity}

/** Define the basic traits of a character
 *
 * @param name    The name of the character
 * @param hp      The health points
 * @param defense The resistance to damage
 * @param weight  The weight of the character
 * @param weapon  The slot for equipping a weapon
 */
abstract class ACharacter(
                           name: String,
                           hp: Int,
                           defense: Int,
                           weight: Int,
                           weapon: Weapon
                        ) extends AEntity(name, hp, defense, weight)
                            with Character {
  private var _weapon = weapon
  override def getWeapon: Weapon = _weapon
  override def setWeapon(wp: Weapon): Unit = {
    wp match {
      case w if w.owner == null =>
        _weapon = wp
        wp.owner = this
      case _ =>
    }
  }

  require(hp >= 0, "Negative HP not allowed")
  require(defense >= 0, "Negative Defense not allowed")
  require(weight > 0,  "Negative or zero Weight not allowed")
}
