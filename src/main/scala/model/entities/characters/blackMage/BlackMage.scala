package model.entities.characters.blackMage

import model.entities.characters.{AMagicalCharacter, IStaffUser, ISwordBearer, IWandUser}

/**
 *
 * @param name    Name given to the mage
 * @param hp      Health points
 * @param defense Defense value
 * @param weight  Weight of the mage
 * @param mana    Mana capacity
 */
class BlackMage (
                  name: String,
                  hp: Int,
                  defense: Int,
                  weight: Int,
                  mana: Int
                ) extends AMagicalCharacter(name,hp,defense,weight,mana)
                    with ISwordBearer with IWandUser with IStaffUser {

}
