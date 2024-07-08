package controller.states

import controller.IGameController

class STransition(
                   controller: IGameController
                 ) extends AGameState(controller) {

  override def step(): Unit = {
    if (controller.turnScheduler.party.get.isDefeated) {
      println("You've been defeated! Too bad.")
      controller.state = GameStateFactory.createState("End",controller)
    }
    else if (controller.turnScheduler.enemyTeam.get.isDefeated) {
      println("You won! Lucky you.")
      controller.state = GameStateFactory.createState("End",controller)
    }
    else if (controller.turnScheduler.charactersFull.isEmpty) {
      println("No entities ready! Raise action bars!")
      controller.turnScheduler.raiseActionBars(controller.raiseConstant)
    }
    else {
      println(s"Turn Phase for ${controller.turnScheduler.atTurn.getName}!")
      controller.turnScheduler.atTurn.actionAble = true
      controller.state = GameStateFactory.createState("Apply Effects", controller)
    }
    controller.step()
  }
}
