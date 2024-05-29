package model.entities.characters.ninja

import exceptions.InvalidActionException
import model.armament.IWeapon
import model.entities.characters.{ACharacter, IBowBearer, ISwordBearer, IWandUser}

/** Creating instance of Ninja
 *
 * @param name    Name given to the mage
 * @param hp      Health points
 * @param defense Defense value
 * @param weight  Weight of the mage
 */
class Ninja (
              name: String,
              hp: Int,
              defense: Int,
              weight: Int
            ) extends ACharacter(name,hp,defense,weight)
                with ISwordBearer with IBowBearer with IWandUser {

  override def equip(wp: IWeapon): Unit = {
    if (wp.getOwner.isEmpty) {
      unEquip()
      wp.equipToNinja(this)
      setWeapon(wp)
    } else {
      throw new InvalidActionException("Assigning Owned Weapon")
    }
  }
}
