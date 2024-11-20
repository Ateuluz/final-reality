package controller.states

import controller.IGameController

class STrueEnd (
                 controller: IGameController
               ) extends AGameState(controller) {

  override def step(): Unit = {
  }
}
