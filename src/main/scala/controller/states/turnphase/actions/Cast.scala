package controller.states.turnphase.actions

import controller.IGameController
import controller.states.{AGameState, GameStateFactory}
import exceptions.InvalidActionException

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

    try {
      controller.turnScheduler.atTurnCharacter.asMagical.canCastSpell(target, spellID)
      controller.turnScheduler.atTurnCharacter.asMagical.castSpell(target, spellID)

      println(s"> ${target.getName} [HP: ${target.getHp}/${target.getHpMax}]\n")
      val ent = controller.turnScheduler.atTurn
      println(s"[Status Update] ${ent.getName} [HP: ${ent.getHp}/${ent.getHpMax}]\n")

      controller.state = GameStateFactory.createState("Turn End", controller)
    } catch {
      case _: InvalidActionException =>
        println("Cannot Complete the Casting")
        controller.state = GameStateFactory.createState("Turn Bifurcation", controller)
    }
    controller.step()
  }

}
