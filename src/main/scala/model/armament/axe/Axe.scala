package model.armament.axe

import model.armament.AWeapon
import model.entities.characters.{IAxeBearer, ICharacter}

/**
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
  override def setOwner(owner: ICharacter): Unit = {
    owner match {
      case axeBearer: IAxeBearer =>
        super.setOwner(axeBearer)
      case _ =>
        println("Cannot set owner. Entity is not an AxeBearer.")
    }
  }
}
