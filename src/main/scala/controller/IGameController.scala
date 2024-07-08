package controller

import controller.states.IGameState
import model.armament.IWeapon
import model.entities.playablecharacters.ICharacter
import model.turnscheduler.ITurnScheduler

import scala.collection.mutable.ArrayBuffer

/** Ateuluz
 *
 * An interface for controlling game states
 */
trait IGameController {

  /** Ateuluz
   *
   * Reset defaults
   */
  def reset(): Unit

  /** Ateuluz
   *
   * Handle Game Flow
   */
  def step(): Unit

  /** Ateuluz
   *
   * Handle in-test Game Flow
   */
  def testStep(): Unit

  /** Ateuluz
   *
   * @return Bool representing testing state
   */
  def isTesting: Boolean

  /** Ateuluz
   *
   * @return The action bar raise constant
   */
  def raiseConstant: Int

  /** Ateuluz
   *
   * @return Stored weapons available
   */
  def weaponInventory: ArrayBuffer[IWeapon]

  /** Ateuluz
   *
   * @return Stored characters available
   */
  def characterInventory: ArrayBuffer[ICharacter]

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

  /** Ateuluz
   *
   * @param prompt Instructions
   * @return Player input
   */
  def getInput(prompt: String): String

}
