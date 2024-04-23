package model.armament

trait IMagicalWeapon extends IWeapon {
  def getMagicAttack: Int
  def constrainMagicAttack(magicAttack: Int): Int
}
