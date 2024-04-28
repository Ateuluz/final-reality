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
  override protected[model] def canBeEquippedBy(character: ICharacter): Boolean = {
    character match {
      case _: IAxeBearer => getOwner.isEmpty
      case _ => false
    }
  }
}
