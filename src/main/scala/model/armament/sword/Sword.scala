package model.armament.sword

import model.armament.AWeapon
import model.entities.playablecharacters.blackmage.BlackMage
import model.entities.playablecharacters.ninja.Ninja
import model.entities.playablecharacters.paladin.Paladin
import model.entities.playablecharacters.warrior.Warrior
import model.entities.playablecharacters.{ICharacter, ISwordBearer}

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
