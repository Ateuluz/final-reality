package controller.states.turnphase.turns

import controller.IGameController
import controller.states.{AGameState, GameStateFactory}
import model.entities.playablecharacters.ICharacter

class EnemyTurn (
                  controller: IGameController
                ) extends AGameState(controller) {

  override def step(): Unit = {
    val attacker = controller.turnScheduler.atTurn
    //val randomInt = scala.util.Random.nextInt()
    val target: ICharacter = controller.turnScheduler.party.get.getMembers.maxBy(_.getHp)//(randomInt)
    println(s"> ${attacker.getName} attacks ${target.getName}!")
    attacker.attack(target)
    println(s"> ${target.getName} | HP left: ${target.getHp}")
    controller.state = GameStateFactory.createState("Turn End", controller)
    controller.step()
  }

}