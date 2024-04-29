package model.teams.party

import model.entities.characters.ICharacter
import model.teams.ATeam

class Party (
              members: Seq[ICharacter]
            ) extends ATeam (3,3,members) {
}
