package model.armament.concrete

import model.armament.AWeapon
import model.entities.playablecharacters.concrete.{Ninja, Warrior, WhiteMage}

/** Ateuluz
 *
 * @param name   The name of the Sword
 * @param attack The attack value
 * @param weight The Sword's weight
 */
class Bow(
           name: String,
           attack: Int,
           weight: Int,
         ) extends AWeapon(name,attack,weight) {

  override def equipToWhiteMage(whiteMage: WhiteMage): Unit =
    setOwner(whiteMage)

  override def equipToWarrior(warrior: Warrior): Unit =
    setOwner(warrior)

  override def equipToNinja(ninja: Ninja): Unit =
    setOwner(ninja)
}
