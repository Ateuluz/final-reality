package model.armament.bow

import model.armament.AWeapon
import model.entities.characters.{IBowBearer, ICharacter}

/**
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
  override protected[model] def canBeEquippedBy(character: ICharacter): Boolean = {
    character match {
      case _: IBowBearer => getOwner.isEmpty
      case _ => false
    }
  }
}
