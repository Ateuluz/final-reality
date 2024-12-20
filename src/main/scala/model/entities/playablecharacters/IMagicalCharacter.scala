package model.entities.playablecharacters

import model.entities.IEntity
import model.spells.ISpell

import scala.collection.mutable.ArrayBuffer

/** Ateuluz
 * When creating a character with magic capabilities
 * this interface will be 'extend ACharacter with' required.
 * It does not extend ICharacter since that is a
 * given trait for all characters in the first place.
 *
 * To be changed if needed later on (unlikely).
 */
trait IMagicalCharacter extends ICharacter {
  /** Ateuluz
   *
   * @return The mana the magical character has left
   */
  def getMana: Int

  /** Ateuluz
   * Public while mana mechanics not disclosed.
   *
   * @param mana The mana we want the character to have
   */
  def setMana(mana: Int): Unit

  /** Ateuluz
   *
   * @param spell The spell to add to prepared spells
   */
  def addSpell(spell: ISpell): Unit

  /** Ateuluz
   *
   * @param ID Index of spell to remove
   */
  def removeSpell(ID: Int): Unit

  /** Ateuluz
   *
   * @return Known (prepared) spells
   */
  def getSpells: ArrayBuffer[ISpell]

  /** Ateuluz
   *
   * @param target Whom to cast the spell on
   * @param spellID What spell to cast
   */
  def canCastSpell(target: IEntity, spellID: Int): Boolean

  /** Ateuluz
   *
   * @param target Whom to cast the spell on
   * @param spellID What spell to cast
   */
  def castSpell(target: IEntity, spellID: Int): Unit

  /** Ateuluz
   *
   * Following the fact that entities don't have innate
   * attack and yet it can be gotten, so happens with
   * magical attack, should the weapon equipped not have
   * magic attack, an invalid action exception will be thrown.
   *
   * @return The magic attack value of the entity, however it may be gotten
   */
  def getMagicAttack: Int
}
