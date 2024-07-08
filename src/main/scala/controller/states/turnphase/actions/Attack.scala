package controller.states.turnphase.actions

import controller.IGameController
import controller.states.{AGameState, GameStateFactory}
import exceptions.InvalidActionException
import model.entities.IEntity

/** Ateuluz
 *
 * @param controller The associated controller for the state
 */
class Attack (
               controller: IGameController
             ) extends AGameState(controller) {

  override def step(): Unit = {

    val target: IEntity = chooseTargetGeneric(controller.turnScheduler.enemyTeam.get.getMembers)

    try {
      println(s"Attack attempt from ${controller.turnScheduler.atTurnCharacter.getName} to ${target.getName} [HP: ${target.getHp}/${target.getHpMax}]")
      if (target.getHp == 0) throw new InvalidActionException("Dead Enemy, attack another.")
      controller.turnScheduler.atTurnCharacter.attack(target)

      println(s"Success! ${target.getName} [HP: ${target.getHp}/${target.getHpMax}]")

      controller.state = GameStateFactory.createState("Turn End", controller)
    } catch {
      case e: InvalidActionException => println(e.getMessage)
    }

    controller.step()
  }

}
