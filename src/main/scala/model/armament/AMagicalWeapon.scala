package model.armament

import exceptions.Require

abstract class AMagicalWeapon(
                              name: String,
                              attack: Int,
                              weight: Int,
                              magicAttack: Int
                            ) extends AWeapon(name,attack,weight)
                                with IMagicalWeapon {
  private val _magicAttack: Int = constrainMagicAttack(magicAttack)
  Require.Stat(magicAttack, "MagicAttack") atLeast 1
  override def getMagicAttack: Int = _magicAttack
  private def constrainMagicAttack(magicAttack: Int): Int =
    magicAttack match {
      case n if n < 1 => 1
      case _ => magicAttack
    }
}
