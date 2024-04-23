package model.entities.characters

import model.armament.Weapon
import model.entities.{AEntity, Entity}

/** Define the basic traits of a character
 *
 * @param name    The name of the character
 * @param hp      The health points
 * @param defense The resistance to damage
 * @param weight  The weight of the character
 * @param magic   The capacity to cast magic
 * @param weapon  The slot for equipping a weapon
 */
abstract class Character(
                        name:String,
                        hp:Int,
                        defense:Int,
                        weight:Int
                        ) extends AEntity(name, hp, defense, weight) {
  val name: String;
  var hp: Int;
  val defense: Int;
  val weight: Int;
  //val magic: Boolean;
  var weapon: Weapon;

  require(hp >= 0, "Negative HP not allowed")
  require(defense >= 0, "Negative Defense not allowed")
  require(weight > 0,  "Negative or zero Weight not allowed")
}
