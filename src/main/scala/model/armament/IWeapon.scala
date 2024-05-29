package model.armament

import model.entities.characters.ICharacter
import model.entities.characters.blackMage.BlackMage
import model.entities.characters.ninja.Ninja
import model.entities.characters.paladin.Paladin
import model.entities.characters.warrior.Warrior
import model.entities.characters.whiteMage.WhiteMage

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
   * @return Boolean representing if the weapon allows for casting
   */
  def getCastCapable: Boolean

//  /**
//   *
//   * @param character the character we intend to equip the weapon to
//   * @return Boolean representing if the character can equip it
//   */
//  protected[model] def canBeEquippedBy(character: ICharacter): Boolean

  /**
   *
   * @param owner The character to which the weapon will be equipped
   */
  protected[model] def setOwner(owner: ICharacter): Unit

  /**
   * Set the owner of the weapon back to None
   */
  protected[model] def unsetOwner(): Unit

  /**
   * Equip this weapon to a Paladin
   * Exception by default
   */
  def equipToPaladin(paladin: Paladin): Unit

  /**
   * Equip this weapon to a Warrior
   * Exception by default
   */
  def equipToWarrior(warrior: Warrior): Unit

  /**
   * Equip this weapon to a Ninja
   * Exception by default
   */
  def equipToNinja(ninja: Ninja): Unit

  /**
   * Equip this weapon to a WhiteMage
   * Exception by default
   */
  def equipToWhiteMage(whiteMage: WhiteMage): Unit

  /**
   * Equip this weapon to a BlackMage
   * Exception by default
   */
  def equipToBlackMage(blackMage: BlackMage): Unit
}
