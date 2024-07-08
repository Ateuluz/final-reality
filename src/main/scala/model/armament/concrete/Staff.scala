package model.armament.concrete

import model.armament.AMagicalWeapon
import model.entities.playablecharacters.concrete.{BlackMage, WhiteMage}

/** Ateuluz
 *
 * @param name        The name of the Sword
 * @param attack      The attack value
 * @param weight      The Sword's weight
 * @param magicAttack The magic attack value
 */
class Staff (
              name: String,
              attack: Int,
              weight: Int,
              magicAttack: Int,
            ) extends AMagicalWeapon(name,attack,weight,magicAttack) {

  override def equipToBlackMage(blackMage: BlackMage): Unit =
    setOwner(blackMage)

  override def equipToWhiteMage(whiteMage: WhiteMage): Unit =
    setOwner(whiteMage)
}