package model.entities.playablecharacters

import exceptions.{InvalidActionException, Require}
import model.entities.IEntity
import model.spells.ISpell

import scala.collection.mutable.ArrayBuffer

/** Ateuluz
 *
 * @param name    The name of the character
 * @param hp      The health points
 * @param defense The resistance to damage
 * @param weight  The weight of the character
 * @param mana    The mana of the character
 */
abstract class AMagicalCharacter(
                                  name: String,
                                  hp: Int,
                                  defense: Int,
                                  weight: Int,
                                  mana: Int
                                ) extends ACharacter(name, hp, defense, weight)
                                  with IMagicalCharacter {

  private var _mana: Int = constrainMana(mana)
  private val _preparedSpells = ArrayBuffer[ISpell]()
  Require.Stat(mana, "Mana") atLeast 0

  /** Ateuluz
   *
   * @return The mana the magical character has left
   */
  override def getMana: Int = _mana

  /** Ateuluz
   * Public while mana mechanics not disclosed.
   *
   * @param mana The mana we want the character to have
   */
  override def setMana(mana: Int): Unit = {
    _mana = constrainMana(mana)
  }

  /** Ateuluz
   *
   * @param mana The original mana value
   * @return The final valid mana value
   */
  private def constrainMana(mana: Int): Int = {
    if (mana < 0) 0
    else mana
  }

  /** Ateuluz
   *
   * @return Known (prepared) spells
   */
  override def getSpells: ArrayBuffer[ISpell] = _preparedSpells

  /** Ateuluz
   *
   * @param spell The spell to add to prepared spells
   */
  override def addSpell(spell: ISpell): Unit =
    _preparedSpells += spell

  /** Ateuluz
   *
   * @param ID Index of spell to remove
   */
  override def removeSpell(ID: Int): Unit =
    if (_preparedSpells.indices.contains(ID))
      _preparedSpells -= _preparedSpells(ID)

  /** Ateuluz
   *
   * Might make protected
   *
   * @param target  Whom to cast the spell on
   * @param spellID What spell to cast
   */
  override def canCastSpell(target: IEntity, spellID: Int): Boolean = {
    if (target.getHp == 0)
      throw new InvalidActionException("Cannot target dead entity")
    if (_mana < _preparedSpells(spellID).getConsumption)
      throw new InvalidActionException("Not enough mana to cast this spell")
    if (getWeapon.isEmpty)
      throw new InvalidActionException("No Weapon equipped")
    if (!getWeapon.get.getCastCapable)
      throw new InvalidActionException("Weapon cannot cast")
    true
  }

  /** Ateuluz
   *
   * @return The magic attack value of the entity, however it may be gotten
   */
  override def getMagicAttack: Int = {
    getWeapon.get.getMagicAttack
  }

  /** Ateuluz
   *
   *  @return Follow up state in turn phase
   */
  override def getTurnPhaseBifurcation: String = "Magical Turn"

  /** Ateuluz
   *
   *  @return The character as magical if is magical
   */
  override def asMagical: IMagicalCharacter = {
    this
  }
}
