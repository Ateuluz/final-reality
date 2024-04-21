package model.characters.whiteMage

import model.armament.Weapon
import model.characters.{BowBearer, MagicalCharacter, StaffUser, WandUser}

/**
 *
 * @param name    Name given to the mage
 * @param hp      Health points
 * @param defense Defense value
 * @param weight  Weight of the mage
 * @param mana    Mana capacity
 * @param weapon  Weapon equipped
 */
class WhiteMage (
                  val name: String,
                  var hp: Int,
                  val defense: Int,
                  val weight: Int,
                  var mana: Int,
                  var weapon: Weapon = null
                ) extends MagicalCharacter() with BowBearer with WandUser with StaffUser {
  def this(
            name: String,
            hp: Int,
            defense: Int,
            weight: Int,
            mana: Int,
          ) = {
    this(name, hp, defense, weight, mana, null)
  }
}
