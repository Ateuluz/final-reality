package elements

/** Define the basic traits of a character
 *
 * @param name    The name of the enemy
 * @param hp      The health points
 * @param defense The resistance to damage
 * @param attack  The attack power
 * @param weight  The weight of the enemy
 * @param magic   The capacity to cast magic
 */
trait Enemy {
  val name: String;
  var hp: Int;
  val defense: Int;
  val attack: Int
  val weight: Int;
  val magic: Boolean;
  var weapon: Weapon;
}