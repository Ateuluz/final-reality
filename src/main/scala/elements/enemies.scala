package elements

/** Define the basic traits of an enemy
 *
 * @param name    The name of the enemy
 * @param hp      The health points
 * @param defense The resistance to damage
 * @param attack  The attack power
 * @param weight  The weight of the enemy
 */
class Enemy(val name: String, var hp: Int, val defense: Int, val attack: Int, val weight: Int) {
  require(hp >= 0, "Negative HP not allowed")
  require(defense >= 0, "Negative Defense not allowed")
  require(attack > 0, "Negative or zero Attack not allowed")
  require(weight > 0, "Negative or zero Weight not allowed")

}