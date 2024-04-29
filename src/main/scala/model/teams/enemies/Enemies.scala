package model.teams.enemies

import model.entities.enemies.IEnemy
import model.teams.ATeam

class Enemies (
                members: IEnemy*
              ) extends ATeam (1,5,members) {
}
