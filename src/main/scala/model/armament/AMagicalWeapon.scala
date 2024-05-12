package model.armament

import exceptions.Require

/**
 *
 * @param name The name for the weapon
 * @param attack The attack for the weapon
 * @param weight The weight for the weapon
 * @param magicAttack The magic attack for the weapon
 */
abstract class AMagicalWeapon(
                              name: String,
                              attack: Int,
                              weight: Int,
                              magicAttack: Int
                            ) extends AWeapon(name,attack,weight)
                                with IMagicalWeapon {
  private val _magicAttack: Int = constrainMagicAttack(magicAttack)
  Require.Stat(magicAttack, "MagicAttack") atLeast 1

  /**
   *
   *  @return The magical attack
   */
  override def getMagicAttack: Int = _magicAttack

  /**
   *
   * @param magicAttack The intended magic attack
   * @return The final valid magick attack
   */
  private def constrainMagicAttack(magicAttack: Int): Int =
    magicAttack match {
      case n if n < 1 => 1
      case _ => magicAttack
    }
}
