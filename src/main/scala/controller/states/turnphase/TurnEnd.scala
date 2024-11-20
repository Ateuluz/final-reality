package controller.states.turnphase

import controller.IGameController
import controller.states.{AGameState, GameStateFactory}

class TurnEnd (
                controller: IGameController
              ) extends AGameState(controller) {

  override def step(): Unit = {
    controller.turnScheduler.reset(
      controller.turnScheduler.atTurn
    )
    controller.turnScheduler.removeDead()
    controller.state = GameStateFactory.createState("Transition", controller)

    waitForResponse()

    controller.step()
  }

}
