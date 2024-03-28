package elements

/** Define the basic traits of a character
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

trait SwordBearer extends Character
trait AxeBearer extends Character
trait BowBearer extends Character
trait WandUser extends Character
trait StaffUser extends Character

class Paladin (
                val name: String,
                var hp: Int,
                val defense: Int,
                val weight: Int,
                var weapon: Weapon = null
              ) extends NonMagicalCharacter() with SwordBearer with AxeBearer {
  def this(
            name: String,
            hp: Int,
            defense: Int,
            weight: Int,
          ) = {
    this(name, hp, defense, weight, null)
  }
}

class Warrior (
                val name: String,
                var hp: Int,
                val defense: Int,
                val weight: Int,
                var weapon: Weapon = null
              ) extends NonMagicalCharacter() with SwordBearer with AxeBearer with BowBearer;

class Ninja (
              val name: String,
              var hp: Int,
              val defense: Int,
              val weight: Int,
              var weapon: Weapon = null
            ) extends NonMagicalCharacter() with SwordBearer with BowBearer with WandUser;

class BlackMage (
                  val name: String,
                  var hp: Int,
                  val defense: Int,
                  val weight: Int,
                  var weapon: Weapon = null
                ) extends MagicalCharacter() with SwordBearer with WandUser with StaffUser;

class WhiteMage (
                  val name: String,
                  var hp: Int,
                  val defense: Int,
                  val weight: Int,
                  var weapon: Weapon = null
                ) extends MagicalCharacter() with BowBearer with WandUser with StaffUser;