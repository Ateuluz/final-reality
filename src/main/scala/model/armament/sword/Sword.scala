package model.armament.sword

import model.armament.AWeapon
import model.entities.characters.{ICharacter, ISwordBearer}

/**
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
  override def setOwner(owner: ICharacter): Unit = {
    owner match {
      case swordBearer: ISwordBearer =>
        super.setOwner(swordBearer)
      case _ =>
        println("Cannot set owner. Entity is not a SwordBearer.")
    }
  }
}
