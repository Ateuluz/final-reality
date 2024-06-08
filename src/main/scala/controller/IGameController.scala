package controller

import model.armament.IWeapon
import model.entities.IEntity
import model.entities.enemies.IEnemy
import model.entities.playablecharacters.{ICharacter, IMagicalCharacter}
import model.spells.ISpell
import model.turnscheduler.ITurnScheduler

import javax.print.attribute.standard.MediaSize.Other

/** Ateuluz
 *
 * An interface for controlling game states
 */
trait IGameController extends ASubject {

  /** Ateuluz
   *
   * A getter for the game state
   *
   * @return Current game state
   */
  def state: IGameState

  /** Ateuluz
   *
   * A setter for the game state
   *
   * @param newState The state we intend to change the controller to
   */
  def state_=(newState: IGameState): Unit

  /** Ateuluz
   *
   * A getter for the associated turn scheduler
   *
   * @return The turn scheduler
   */
  def turnScheduler: ITurnScheduler

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

  //<editor-fold desc="Controlling States">
  /** Ateuluz
   *
   * A method that that loops over transition state
   */
  def loop(): Unit

  /** Ateuluz
   *
   * A method that will begin the turn taking process
   */
  def takeTurns(): Unit

  /** Ateuluz
   *
   * A method to end the game
   */
  def end(): Unit
  //</editor-fold>

}
