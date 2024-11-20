package model.armament

import model.entities.playablecharacters.ICharacter
import model.entities.playablecharacters.concrete.{BlackMage, Ninja, Paladin, Warrior, WhiteMage}

/** Ateuluz
 * Trait defining a weapons methods
 */
trait IWeapon {
  /** Ateuluz
   *
   * @return weapons name
   */
  def getName: String

  /** Ateuluz
   *
   * @return weapons attack
   */
  def getAttack: Int

  /** Ateuluz
   *
   * @return The magical attack
   */
  def getMagicAttack: Int

  /** Ateuluz
   *
   * @return weapons weight
   */
  def getWeight: Int

  /** Ateuluz
   *
   * @return weapons owner if any else None
   */
  def getOwner: Option[ICharacter]

  /** Ateuluz
   *
   * @return Boolean representing if the weapon allows for casting
   */
  def getCastCapable: Boolean

//  /** Ateuluz
//   *
//   * @param character the character we intend to equip the weapon to
//   * @return Boolean representing if the character can equip it
//   */
//  protected[model] def canBeEquippedBy(character: ICharacter): Boolean

  /** Ateuluz
   *
   * @param owner The character to which the weapon will be equipped
   */
  protected[model] def setOwner(owner: ICharacter): Unit

  /** Ateuluz
   * Set the owner of the weapon back to None
   */
  protected[model] def unsetOwner(): Unit

  /** Ateuluz
   * Equip this weapon to a Paladin
   * Exception by default
   */
  def equipToPaladin(paladin: Paladin): Unit

  /** Ateuluz
   * Equip this weapon to a Warrior
   * Exception by default
   */
  def equipToWarrior(warrior: Warrior): Unit

  /** Ateuluz
   * Equip this weapon to a Ninja
   * Exception by default
   */
  def equipToNinja(ninja: Ninja): Unit

  /** Ateuluz
   * Equip this weapon to a WhiteMage
   * Exception by default
   */
  def equipToWhiteMage(whiteMage: WhiteMage): Unit

  /** Ateuluz
   * Equip this weapon to a BlackMage
   * Exception by default
   */
  def equipToBlackMage(blackMage: BlackMage): Unit
}
