package model.armament

import model.entities.characters.ICharacter

/**
 * Trait defining a weapons methods
 */
trait IWeapon {
  /**
   *
   * @return weapons name
   */
  def getName: String

  /**
   *
   * @return weapons attack
   */
  def getAttack: Int

  /**
   *
   * @return weapons weight
   */
  def getWeight: Int
  /**
   *
   * @return weapons owner if any else None
   */
  def getOwner: Option[ICharacter]

  /**
   *
   * @param character the character we intend to equip the weapon to
   * @return Boolean representing if the character can equip it
   */
  protected[model] def canBeEquippedBy(character: ICharacter): Boolean

  /**
   *
   * @param owner The character to which the weapon will be equipped
   */
  protected[model] def setOwner(owner: ICharacter): Unit

  /**
   * Set the owner of the weapon back to None
   */
  protected[model] def unsetOwner(): Unit
}
