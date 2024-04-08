package model.armament

trait MagicalWeapon extends Weapon {
  val magicAttack: Int

  require(magicAttack >= 0, "Negative or zero MagicAttack not allowed")
}
