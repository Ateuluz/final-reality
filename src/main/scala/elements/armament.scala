package elements

/**
 *
 */
trait Weapon {
  val name: String
  val attack: Int
  val weight: Int
  var owner: Character

}

/**
 *
 * @param magicAttack The attack for spells
 */
trait MagicalWeapon{
  val magicAttak: Int
}

/**
 *
 */
trait NonMagicalWeapon

/**
 *
 * @param name   The name of the Sword
 * @param attack The attack value
 * @param weight The Sword's weight
 * @param owner  The Sword's owner
 */
class Sword(
             val name: String,
             val attack: Int,
             val weight: Int,
             var owner: Character,
           ) extends NonMagicalWeapon {

  def this(name: String, attack: Int, weight: Int) = {
    this(name, attack, weight, null)
  }
}