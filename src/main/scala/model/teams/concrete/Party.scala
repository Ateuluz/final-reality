package model.teams.concrete

import model.entities.playablecharacters.ICharacter
import model.teams.ATeam

/** Ateuluz
 *
 * @param members The members of the character team
 */
class Party (
              members: ICharacter*
            ) extends ATeam[ICharacter] (3,3,members) {
}
