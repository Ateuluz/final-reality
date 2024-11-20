package controller.states

import controller.IGameController

class SEnd(
            controller: IGameController
          ) extends AGameState(controller) {

  override def step(): Unit = {
    if (controller.getInput("0: Exit\nAny: Re-match") == "0") {
      println("Game End.")
      controller.state = GameStateFactory.createState("TrueEnd",controller)
    }
    else {
      println("Re-match begin! Good luck.")
      controller.reset()
      controller.state = GameStateFactory.createState("Start",controller)
    }
    controller.step()
  }
}
