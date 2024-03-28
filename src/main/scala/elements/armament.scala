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
trait MagicalWeapon extends Weapon {
  val magicAttack: Int
}

/**
 *
 */
trait NonMagicalWeapon extends Weapon

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

/**
 *
 * @param name   The name of the Sword
 * @param attack The attack value
 * @param weight The Sword's weight
 * @param owner  The Sword's owner
 */
class Axe(
           val name: String,
           val attack: Int,
           val weight: Int,
           var owner: Character,
         ) extends NonMagicalWeapon {

  def this(name: String, attack: Int, weight: Int) = {
    this(name, attack, weight, null)
  }
}

/**
 *
 * @param name   The name of the Sword
 * @param attack The attack value
 * @param weight The Sword's weight
 * @param owner  The Sword's owner
 */
class Bow(
           val name: String,
           val attack: Int,
           val weight: Int,
           var owner: Character,
         ) extends NonMagicalWeapon {

  def this(name: String, attack: Int, weight: Int) = {
    this(name, attack, weight, null)
  }
}

/**
 *
 * @param name        The name of the Sword
 * @param attack      The attack value
 * @param weight      The Sword's weight
 * @param magicAttack The magic attack value
 * @param owner       The Sword's owner
 */
class Wand (
             val name: String,
             val attack: Int,
             val weight: Int,
             val magicAttack: Int,
             var owner: Character,
           ) extends MagicalWeapon {

  def this(name: String, attack: Int, weight: Int, magicAttack: Int) = {
    this(name, attack, weight, magicAttack, null)
  }
}

/**
 *
 * @param name        The name of the Sword
 * @param attack      The attack value
 * @param weight      The Sword's weight
 * @param magicAttack The magic attack value
 * @param owner       The Sword's owner
 */
class Staff (
             val name: String,
             val attack: Int,
             val weight: Int,
             val magicAttack: Int,
             var owner: Character,
           ) extends MagicalWeapon {

  def this(name: String, attack: Int, weight: Int, magicAttack: Int) = {
    this(name, attack, weight, magicAttack, null)
  }
}