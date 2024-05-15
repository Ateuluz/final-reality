package model.entities.characters.paladin

import model.armament.IWeapon
import model.entities.characters.{ACharacter, IAxeBearer, ISwordBearer}

/** Creating instance of Paladin
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

  override def equip(wp: IWeapon): Unit =
    wp.equipToPaladin(this)
}
