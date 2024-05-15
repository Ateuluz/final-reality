package model.armament.wand

import model.armament.AMagicalWeapon
import model.entities.characters.blackMage.BlackMage
import model.entities.characters.ninja.Ninja
import model.entities.characters.whiteMage.WhiteMage
import model.entities.characters.{ICharacter, IWandUser}

/**
 *
 * @param name        The name of the Sword
 * @param attack      The attack value
 * @param weight      The Sword's weight
 * @param magicAttack The magic attack value
 */
class Wand (
             name: String,
             attack: Int,
             weight: Int,
             magicAttack: Int,
           ) extends AMagicalWeapon(name,attack,weight,magicAttack) {
  /**
   *
   * @param character the character we intend to equip the weapon to
   *  @return Boolean representing if the character can equip it
   */
  override protected[model] def canBeEquippedBy(character: ICharacter): Boolean = {
    character match {
      case _: IWandUser => getOwner.isEmpty
      case _ => false
    }
  }

  override def equipToNinja(ninja: Ninja): Unit =
    setOwner(ninja)

  override def equipToBlackMage(blackMage: BlackMage): Unit =
    setOwner(blackMage)

  override def equipToWhiteMage(whiteMage: WhiteMage): Unit =
    setOwner(whiteMage)
}
