package elements

/**
 *
 */
trait Weapon {
  val name: String
  val attack: Int
  val weight: Int
  var owner: Character

  require(attack >= 0, "Negative or zero Attack not allowed")
  require(weight >= 0, "Negative or zero Weight not allowed")
}

/**
 *
 * @param magicAttack The attack for spells
 */
trait MagicalWeapon extends Weapon {
  val magicAttack: Int

  require(magicAttack >= 0, "Negative or zero MagicAttack not allowed")
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
 * @param _owner The Sword's owner
 */
class Sword(
             val name: String,
             val attack: Int,
             val weight: Int,
             var _owner: SwordBearer
           ) extends NonMagicalWeapon {
  override var owner: Character = _owner

  def this(name: String, attack: Int, weight: Int) = {
    this(name, attack, weight, null)
  }
}

/**
 *
 * @param name   The name of the Sword
 * @param attack The attack value
 * @param weight The Sword's weight
 * @param _owner The Sword's owner
 */
class Axe(
           val name: String,
           val attack: Int,
           val weight: Int,
           var _owner: SwordBearer
         ) extends NonMagicalWeapon {
  override var owner: Character = _owner

  def this(name: String, attack: Int, weight: Int) = {
    this(name, attack, weight, null)
  }
}

/**
 *
 * @param name   The name of the Sword
 * @param attack The attack value
 * @param weight The Sword's weight
 * @param _owner  The Sword's owner
 */
class Bow(
           val name: String,
           val attack: Int,
           val weight: Int,
           var _owner: SwordBearer
         ) extends NonMagicalWeapon {
  override var owner: Character = _owner

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
 * @param _owner      The Sword's owner
 */
class Wand (
             val name: String,
             val attack: Int,
             val weight: Int,
             val magicAttack: Int,
             var _owner: WandUser,
           ) extends MagicalWeapon {
  override var owner: Character = _owner

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
 * @param _owner      The Sword's owner
 */
class Staff (
             val name: String,
             val attack: Int,
             val weight: Int,
             val magicAttack: Int,
             var _owner: StaffUser,
           ) extends MagicalWeapon {
  override var owner: Character = _owner

  def this(name: String, attack: Int, weight: Int, magicAttack: Int) = {
    this(name, attack, weight, magicAttack, null)
  }
}