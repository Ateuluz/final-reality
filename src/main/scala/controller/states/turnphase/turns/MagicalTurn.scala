package controller.states.turnphase.turns

import controller.IGameController
import controller.states.{AGameState, GameStateFactory}
import exceptions.InvalidActionException
import model.entities.enemies.Enemy

/** Ateuluz
 *
 * @param controller The associated controller for the state
 */
class MagicalTurn (
                    controller: IGameController
                  ) extends AGameState(controller) {

  private def canSwitchToCastState: Boolean = {
    val character = controller.turnScheduler.atTurnCharacter
    character.asMagical.getSpells.exists(
      spell => character.asMagical.canCastSpell(
        new Enemy("Mock Enemy", 1,1,1,1),
        character.asMagical.getSpells.indexOf(spell)
      )
    )
  }

  override def step(): Unit = {
    val opt = controller.getInput(
      "Choose an action: \n0: Equip Weapon \n1: Attack \n2: Cast Spell"
    )
    if (opt == "0") {
      controller.state = GameStateFactory.createState("Equip", controller)
    }
    else if (opt == "1") {
      controller.state = GameStateFactory.createState("Attack", controller)
    }
    else if (opt == "2") {
      try {
        if (canSwitchToCastState) {
          controller.state = GameStateFactory.createState("Cast", controller)
        }
        else {
          println("Mage Cast Conditions not met")
        }
      } catch {
        case _: InvalidActionException =>
          println("Mage Cast Conditions not met")
      }

    }
    controller.step()
  }

}