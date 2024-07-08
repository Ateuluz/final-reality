package controller.states

import controller.IGameController

class STransition(
                   controller: IGameController
                 ) extends AGameState(controller) {

  override def step(): Unit = {
    println("")
    if (controller.turnScheduler.party.get.isDefeated) {
      println("You've been defeated! Too bad.")
      controller.state = GameStateFactory.createState("End",controller)
      waitForResponse()
    }
    else if (controller.turnScheduler.enemyTeam.get.isDefeated) {
      println("You won! Lucky you.")
      controller.state = GameStateFactory.createState("End",controller)
      waitForResponse()
    }
    else if (controller.turnScheduler.charactersFull.isEmpty) {
      println("No entities ready! Raise action bars!")
      controller.turnScheduler.raiseActionBars(controller.raiseConstant)
    }
    else {
      val ent = controller.turnScheduler.atTurn
      println(s"\n>>> Turn Phase for ${ent.getName}! [HP: ${ent.getHp}/${ent.getHpMax}]")
      controller.turnScheduler.atTurn.actionAble = true
      controller.state = GameStateFactory.createState("Apply Effects", controller)
      waitForResponse()
    }

    controller.step()
  }
}
