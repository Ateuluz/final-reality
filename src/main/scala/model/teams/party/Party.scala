package model.teams.party

import model.entities.characters.ICharacter
import model.teams.ATeam

/**
 *
 * @param members The members of the character team
 */
class Party (
              members: ICharacter*
            ) extends ATeam (3,3,members) {
}
