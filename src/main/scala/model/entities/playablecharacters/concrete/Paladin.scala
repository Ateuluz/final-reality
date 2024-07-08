package model.entities.playablecharacters.concrete

import exceptions.InvalidActionException
import model.armament.IWeapon
import model.entities.playablecharacters.typetraits.{IAxeBearer, ISwordBearer}
import model.entities.playablecharacters.ACharacter

/** Ateuluz Creating instance of Paladin
 *
 * @param name    Name given to the mage
 * @param hp      Health points
 * @param defense Defense value
 * @param weight  Weight of the mage
 */
class Paladin (
                name: String,
                hp: Int,
                defense: Int,
                weight: Int
              ) extends ACharacter(name,hp,defense,weight)
                  with ISwordBearer with IAxeBearer {

  override def equip(wp: IWeapon): Unit = {
    if (wp.getOwner.isEmpty) {
      unEquip()
      wp.equipToPaladin(this)
      setWeapon(wp)
    } else {
      throw new InvalidActionException("Assigning Owned Weapon")
    }
  }
}
