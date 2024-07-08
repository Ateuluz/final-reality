package controller.states.turnphase

import controller.IGameController
import controller.states.{AGameState, GameStateFactory}

/** Ateuluz
 *
 * @param controller The associated controller for the state
 */
class ApplyEffects (
                      controller: IGameController
                    ) extends AGameState(controller) {

  /** Ateuluz
   *
   * Game flow
   */
  override def step(): Unit = {
    val ent = controller.turnScheduler.atTurn
    ent.effectsApply()
    if (ent.actionAble) {
      println("Moving to turn!")
      controller.state = GameStateFactory.createState("Turn Bifurcation",controller)
    } else {
      println("Cannot take action!")
      controller.state = GameStateFactory.createState("Turn End", controller)
    }

    controller.step()
  }

}
