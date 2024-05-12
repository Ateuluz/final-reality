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
  /**
   *
   * @param character the character we intend to equip the weapon to
   *  @return Boolean representing if the character can equip it
   */
  override protected[model] def canBeEquippedBy(character: ICharacter): Boolean = {
    character match {
      case _: ISwordBearer => getOwner.isEmpty
      case _ => false
    }
  }
}
