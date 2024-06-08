package controller

import model.armament.IWeapon
import model.entities.IEntity
import model.entities.enemies.IEnemy
import model.entities.playablecharacters.{ICharacter, IMagicalCharacter}
import model.spells.ISpell

/** Ateuluz
 *
 * An interface to handle states and their methods
 */
trait IGameState {

  /** Ateuluz
   *
   * Step method to handle timings
   */
  def step(): Unit

  /** Ateuluz
   *
   * Method to get to the main game loop
   * Relevant end game decisions checked here
   */
  def loop(): Unit

  /** Ateuluz
   *
   * Method to get to turn taking loop
   * All actions will be defined in such loop
   */
  def takeTurns(): Unit

  /** Ateuluz
   *
   * Method to take us to the end game state
   */
  def end(): Unit

  //<editor-fold desc="Required Methods">
  /** Ateuluz
   *
   * Begin the game
   */
  def gameBegin(): Unit

  /** Ateuluz
   *
   * @param unit The attacker
   * @param other The defender
   */
  def unitAttackOther(unit: IEntity, other: IEntity): Unit

  /** Ateuluz
   *
   * @param mage Mage attacking
   * @param target Enemy defending
   * @param spell Spell cast
   */
  def mageCast(mage: IMagicalCharacter, target: IEnemy, spell: ISpell): Unit

  /** Ateuluz
   *
   * @param char Character equipping
   * @param weapon Weapon equipped
   */
  def characterEquip(char: ICharacter, weapon: IWeapon): Unit
  //</editor-fold>
}
