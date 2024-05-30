package model.armament

import exceptions.{InvalidActionException, InvalidHolderException, Require}
import model.entities.playablecharacters.ICharacter
import model.entities.playablecharacters.blackmage.BlackMage
import model.entities.playablecharacters.ninja.Ninja
import model.entities.playablecharacters.paladin.Paladin
import model.entities.playablecharacters.warrior.Warrior
import model.entities.playablecharacters.whitemage.WhiteMage

/** Ateuluz
 *
 * @param name The name for the weapon
 * @param attack The attack for the weapon
 * @param weight The weight for the weapon
 */
abstract class AWeapon (
                         name: String,
                         attack: Int,
                         weight: Int
                       ) extends IWeapon {
  private val _name: String = name
  private val _attack: Int = constrainAttack(attack)
  private val _weight: Int = constrainWeight(weight)
  private var _owner: Option[ICharacter] = None
  Require.Stat(attack, "Attack") atLeast 1
  Require.Stat(weight, "Weight") atLeast 0

  /** Ateuluz
   *
   *  @return weapons name
   */
  override def getName: String = _name

  /** Ateuluz
   *
   *  @return weapons attack
   */
  override def getAttack: Int = _attack

  /** Ateuluz
   *
   *  @return The magical attack
   */
  override def getMagicAttack: Int =
    throw new InvalidActionException("Cannot get magic attack of this weapon.")

  /** Ateuluz
   *
   *  @return weapons weight
   */
  override def getWeight: Int = _weight

  /** Ateuluz
   *
   *  @return weapons owner if any else None
   */
  override def getOwner: Option[ICharacter] = _owner

  /** Ateuluz
   *
   *  @return Boolean representing if the weapon allows for casting
   */
  override def getCastCapable: Boolean = false

  /** Ateuluz
   *
   * @param attack The intended attack value
   * @return The final valid attack value
   */
  private def constrainAttack(attack: Int): Int =
    attack match {
      case n if n < 1 => 1
      case _ => attack
    }

  /** Ateuluz
   *
   * @param weight The intended weight value
   * @return The final valid weight value
   */
  private def constrainWeight(weight: Int): Int =
    weight match {
      case n if n < 0 => 0
      case _ => weight
    }

  /** Ateuluz
   *
   * @param owner The character to which the weapon will be equipped
   */
  override protected[model] def setOwner(owner: ICharacter): Unit = {
    /*_owner match {
      case Some(char) => char.unEquip()
      case _ =>
    }*/ // Case if owner override is possible
    _owner = Some(owner)
    /*owner.setWeapon(this)*/ // Already handled in characters
  }

  /** Ateuluz
   * Set the owner of the weapon back to NoneSet the owner of the weapon back to None
   */
  override protected[model] def unsetOwner(): Unit = {
    _owner = None
  }

  /** Ateuluz
   *
   * @param paladin The character to equip the weapon with
   */
  override def equipToPaladin(paladin: Paladin): Unit = {
    throw new InvalidHolderException("Paladin character cannot equip this type of weapon.")
  }

  /** Ateuluz
   *
   * @param warrior The character to equip the weapon with
   */
  override def equipToWarrior(warrior: Warrior): Unit = {
    throw new InvalidHolderException("Warrior character cannot equip this type of weapon.")
  }

  /** Ateuluz
   *
   * @param ninja The character to equip the weapon with
   */
  override def equipToNinja(ninja: Ninja): Unit = {
    throw new InvalidHolderException("Ninja character cannot equip this type of weapon.")
  }

  /** Ateuluz
   *
   * @param whiteMage The character to equip the weapon with
   */
  override def equipToWhiteMage(whiteMage: WhiteMage): Unit = {
    throw new InvalidHolderException("WhiteMage character cannot equip this type of weapon.")
  }

  /** Ateuluz
   *
   * @param blackMage The character to equip the weapon with
   */
  override def equipToBlackMage(blackMage: BlackMage): Unit = {
    throw new InvalidHolderException("BlackMage character cannot equip this type of weapon.")
  }
}
