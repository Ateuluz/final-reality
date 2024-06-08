package controller

import exceptions.InvalidStateException
import model.armament.IWeapon
import model.entities.IEntity
import model.entities.enemies.IEnemy
import model.entities.playablecharacters.{ICharacter, IMagicalCharacter}
import model.spells.ISpell

/** Ateuluz
 *
 * An abstract class for defining Game states and their methods
 *
 * @param controller The associated controller for the state
 */
abstract class AGameState (
                            val controller: IGameController
                          ) extends IGameState {

  /** Ateuluz
   *
   * To improve readability
   */
  private def raiseException(): Unit = throw new InvalidStateException("This State cannot handle the intended action")

  /** Ateuluz
   *
   * Step method to handle game flow
   */
  override def step(): Unit = raiseException()

  /** Ateuluz
   *
   * Method to get to the main game loop
   * Relevant end game decisions checked here
   */
  override def loop(): Unit = raiseException()

  /** Ateuluz
   *
   * Method to get to turn taking loop
   * All actions will be defined in such loop
   */
  override def takeTurns(): Unit = raiseException()

  /** Ateuluz
   *
   * Method to take us to the end game state
   */
  override def end(): Unit = raiseException()

  //<editor-fold desc="Required Methods">
  /** Ateuluz
   *
   * Begin the game
   */
  def gameBegin(): Unit = raiseException()

  /** Ateuluz
   *
   * @param unit The attacker
   * @param other The defender
   */
  def unitAttackOther(unit: IEntity, other: IEntity): Unit = raiseException()

  /** Ateuluz
   *
   * @param mage Mage attacking
   * @param target Enemy defending
   * @param spell Spell cast
   */
  def mageCast(mage: IMagicalCharacter, target: IEnemy, spell: ISpell): Unit = raiseException()

  /** Ateuluz
   *
   * @param char Character equipping
   * @param weapon Weapon equipped
   */
  def characterEquip(char: ICharacter, weapon: IWeapon): Unit = raiseException()
  //</editor-fold>
}
