package controller

import controller.states.STrueBeginning
import model.turnscheduler.{ITurnScheduler, TurnScheduler}
import controller.inputhandler.IInputHandler

/** Ateuluz
 *
 * An abstract class for implementing a game controller
 * All States will call upon a similar method on their State
 */
abstract class AGameController (
                                 val inputHandler: IInputHandler,
                                 private var _isTesting: Boolean = false
                               ) extends IGameController {

  private var _state: IGameState = new STrueBeginning(this)

  private val _turnScheduler: ITurnScheduler = new TurnScheduler

  /** Ateuluz
   *
   * Handle Game Flow
   */
  override def step(): Unit = if (!isTesting) _state.step()

  /** Ateuluz
   *
   * Handle in-test Game Flow
   */
  override def testStep(): Unit = _state.step()

  /** Ateuluz
   *
   *  @return Bool representing testing state
   */
  override def isTesting: Boolean = _isTesting

  /** Ateuluz
   *
   * @param prompt Instructions
   * @return Player input
   */
  override def getInput(prompt: String): String = {
    inputHandler.getInput(prompt)
  }

  /** Ateuluz
   *
   *  @return Current game state
   */
  override def state: IGameState = _state

  /** Ateuluz
   *
   * @param newState The state we intend to change the controller to
   */
  override def state_=(newState: IGameState): Unit = _state = newState

  /** Ateuluz
   *
   *  @return The turn scheduler
   */
  override def turnScheduler: ITurnScheduler = _turnScheduler

}
