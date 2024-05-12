package model.armament

/**
 * Trait defining methods of a Magical Weapon
 */
trait IMagicalWeapon extends IWeapon {
  /**
   *
   * @return The magical attack
   */
  def getMagicAttack: Int
}
