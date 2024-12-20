package model.armament.concrete

import model.armament.AWeapon
import model.entities.playablecharacters.concrete.{Paladin, Warrior}

/** Ateuluz
 *
 * @param name   The name of the Sword
 * @param attack The attack value
 * @param weight The Sword's weight
 */
class Axe(
           name: String,
           attack: Int,
           weight: Int,
         ) extends AWeapon(name,attack,weight) {

  override def equipToPaladin(paladin: Paladin): Unit =
    setOwner(paladin)

  override def equipToWarrior(warrior: Warrior): Unit =
    setOwner(warrior)
}
