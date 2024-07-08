package controller.states.turnphase.actions

import controller.IGameController
import controller.states.{AGameState, GameStateFactory}

/** Ateuluz
 *
 * @param controller The associated controller for the state
 */
class Cast (
             controller: IGameController
           ) extends AGameState(controller) {

  override def step(): Unit = {

    val spellID: Int    = chooseSpell(controller.turnScheduler.atTurnCharacter.asMagical.getSpells)

    val strategy = controller.turnScheduler.atTurnCharacter.asMagical.getSpells(spellID).targetStrategy
    val targetTm = strategy.getTargets(controller.turnScheduler)
    val target   = chooseTargetGeneric(targetTm.getMembers)

    if (controller.turnScheduler.atTurnCharacter.asMagical.canCastSpell(target, spellID)) {
      controller.turnScheduler.atTurnCharacter.asMagical.castSpell(target, spellID)

      controller.state = GameStateFactory.createState("Turn End", controller)
    }
    else {
      println("Cannot Complete the Casting")
    }

    controller.step()
  }

}
