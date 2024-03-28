package elements

/** Define the basic traits of a character
 *
 *
 * @param name    The name of the character
 * @param hp      The health points
 * @param defense The resistance to damage
 * @param weight  The weight of the character
 * @param magic   The capacity to cast magic
 * @param weapon  The slot for equipping a weapon
 */
trait Character {
  val name: String;
  var hp: Int;
  val defense: Int;
  val weight: Int;
  val magic: Boolean;
  var weapon: Weapon;
}

trait NonMagicalCharacter extends Character {
  val magic: Boolean = false;
}

trait MagicalCharacter extends Character {
  val magic: Boolean = true;
}

class Paladin extends NonMagicalCharacter();

class Warrior extends NonMagicalCharacter();

class Ninja extends NonMagicalCharacter();

class DarkMage extends MagicalCharacter();

class WhiteMage extends MagicalCharacter();