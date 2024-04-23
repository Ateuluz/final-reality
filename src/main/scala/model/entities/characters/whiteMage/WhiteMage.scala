package model.entities.characters.whiteMage

import model.entities.characters.{AMagicalCharacter, IBowBearer, IStaffUser, IWandUser}

/**
 *
 * @param name    Name given to the mage
 * @param hp      Health points
 * @param defense Defense value
 * @param weight  Weight of the mage
 * @param mana    Mana capacity
 */
class WhiteMage (
                  name: String,
                  hp: Int,
                  defense: Int,
                  weight: Int,
                  mana: Int
                ) extends AMagicalCharacter(name,hp,defense,weight,mana)
                    with IBowBearer with IWandUser with IStaffUser {

}
