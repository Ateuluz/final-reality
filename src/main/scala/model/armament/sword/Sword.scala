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
  override protected[model] def canBeEquippedBy(character: ICharacter): Boolean = {
    character match {
      case _: ISwordBearer => getOwner.isEmpty
      case _ => false
    }
  }
}
