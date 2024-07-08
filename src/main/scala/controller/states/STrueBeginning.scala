package controller.states

import controller.IGameController

class STrueBeginning(
                      controller: IGameController
                    ) extends AGameState(controller) {

  override def step(): Unit = {

    println("\n==============================================\n==============================================\n")
    controller.getInput("[ENTER TO BEGIN]")

    controller.state = GameStateFactory.createState("Start", controller)
    controller.step()
  }

}
