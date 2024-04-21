package model.armament.axe

import model.armament.NonMagicalWeapon
import model.characters.{AxeBearer, Character}

/**
 *
 * @param name   The name of the Sword
 * @param attack The attack value
 * @param weight The Sword's weight
 * @param _owner The Sword's owner
 */
class Axe(
           val name: String,
           val attack: Int,
           val weight: Int,
           var _owner: AxeBearer
         ) extends NonMagicalWeapon {
  override var owner: Character = _owner

  def this(name: String, attack: Int, weight: Int) = {
    this(name, attack, weight, null)
  }
}
