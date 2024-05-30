package model.armament.bow

import model.armament.AWeapon
import model.entities.characters.ninja.Ninja
import model.entities.characters.warrior.Warrior
import model.entities.characters.whiteMage.WhiteMage
import model.entities.characters.{IBowBearer, ICharacter}

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
