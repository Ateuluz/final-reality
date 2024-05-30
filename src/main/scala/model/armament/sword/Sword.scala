package model.armament.sword

import model.armament.AWeapon
import model.entities.characters.blackmage.BlackMage
import model.entities.characters.ninja.Ninja
import model.entities.characters.paladin.Paladin
import model.entities.characters.warrior.Warrior
import model.entities.characters.{ICharacter, ISwordBearer}

/** Ateuluz
 *
 * @param name   The name of the Sword
 * @param attack The attack value
 * @param weight The Sword's weight
 */
class Sword(
             name: String,
             attack: Int,
             weight: Int
           ) extends AWeapon(name,attack,weight) {

  override def equipToPaladin(paladin: Paladin): Unit =
    setOwner(paladin)

  override def equipToWarrior(warrior: Warrior): Unit =
    setOwner(warrior)

  override def equipToNinja(ninja: Ninja): Unit =
    setOwner(ninja)

  override def equipToBlackMage(blackMage: BlackMage): Unit =
    setOwner(blackMage)
}
