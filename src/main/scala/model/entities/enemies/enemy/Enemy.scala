package model.entities.enemies.enemy

import model.entities.AEntity
import model.entities.enemies.AEnemy

/** Define the basic traits of an enemy
 *
 * @param name    The name of the enemy
 * @param hp      The health points
 * @param defense The resistance to damage
 * @param attack  The attack power
 * @param weight  The weight of the enemy
 */
class Enemy(
             name: String,
             hp: Int,
             defense: Int,
             attack: Int,
             weight: Int
           ) extends AEnemy(name,hp,defense,attack,weight) {

}
