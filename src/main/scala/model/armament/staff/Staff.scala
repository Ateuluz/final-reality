package model.armament.staff

import model.armament.MagicalWeapon
import model.characters.{Character, StaffUser}

/**
 *
 * @param name        The name of the Sword
 * @param attack      The attack value
 * @param weight      The Sword's weight
 * @param magicAttack The magic attack value
 * @param _owner      The Sword's owner
 */
class Staff (
              val name: String,
              val attack: Int,
              val weight: Int,
              val magicAttack: Int,
              var _owner: StaffUser,
            ) extends MagicalWeapon {
  override var owner: Character = _owner

  def this(name: String, attack: Int, weight: Int, magicAttack: Int) = {
    this(name, attack, weight, magicAttack, null)
  }
}
