package model.entities.characters.warrior

import model.armament.IWeapon
import model.entities.characters.{ACharacter, IAxeBearer, IBowBearer, ISwordBearer}

/** Creating instance of Warrior
 *
 * @param name    Name given to the mage
 * @param hp      Health points
 * @param defense Defense value
 * @param weight  Weight of the mage
 */
class Warrior (
                name: String,
                hp: Int,
                defense: Int,
                weight: Int
              ) extends ACharacter(name,hp,defense,weight)
                  with ISwordBearer with IAxeBearer with IBowBearer {

  override def equip(wp: IWeapon): Unit = {
    if (wp.getOwner.isEmpty) {
      unEquip()
      wp.equipToWarrior(this)
      setWeapon(wp)
    }
  }
}
