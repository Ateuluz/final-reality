package controller

/** Ateuluz
 *
 * The state that serves as main loop
 *
 * @param controller The associated controller for the state
 */
class STransition (
                    controller: IGameController
                  ) extends AGameState(controller) {

  /** Ateuluz
   * 
   * Handle all transition mechanics.
   * If the endgame conditions are met, End State will be forced
   */
  override def step(): Unit =
    if (controller.turnScheduler.endgame) {
      end()
    }
    else if (controller.turnScheduler.charactersFull.isEmpty) {
      val raiseConstant = 10
      controller.turnScheduler.raiseActionBars(raiseConstant)
      println("Action Bars Raised!")
    }
    else {
      takeTurns()
    }

  /** Ateuluz
   *
   * Transition to TurnTaking State
   */
  override def takeTurns(): Unit = {
    println("There's Entities waiting to take turns!")

    controller.state = GameStateFactory.createState("TurnTaking", controller)
  }

  /** Ateuluz
   *
   * Should the game end by force or because of endgame conditions,
   * we transition to End State
   */
  override def end(): Unit = {
    println("Game ended!")

    controller.state = GameStateFactory.createState("End", controller)
  }
}
