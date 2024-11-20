package model.teams.concrete

import model.entities.enemies.IEnemy
import model.teams.ATeam

/** Ateuluz
 *
 * @param members The members of the enemy team
 */
class Enemies (
                members: IEnemy*
              ) extends ATeam[IEnemy] (1,5,members) {
}
