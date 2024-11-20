package controller.states.turnphase

import controller.IGameController
import controller.states.{AGameState, GameStateFactory}

/** Ateuluz
 *
 * @param controller The associated controller for the state
 */
class TurnBifurcation (
                        controller: IGameController
                      ) extends AGameState(controller) {

  override def step(): Unit = {
    println("")
    val ent = controller.turnScheduler.atTurn
    val state = ent.getTurnPhaseBifurcation
    controller.state = GameStateFactory.createState(state,controller)
    controller.step()
  }

}
