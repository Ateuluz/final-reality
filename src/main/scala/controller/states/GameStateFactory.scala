package controller.states

import controller.{IGameController, IGameState}
import controller.states.start.StartPhase
import controller.states.turnphase.actions.{Attack, Cast, Equip}
import controller.states.turnphase.turns.{EnemyTurn, MagicalTurn, RegularTurn}
import controller.states.turnphase.{ApplyEffects, TurnBifurcation, TurnEnd}

/** Ateuluz
 *
 * An object that simplifies the State handling process
 */
object GameStateFactory {

  def createState(stateType: String, controller: IGameController): IGameState = stateType match {
    case "Start"            => new StartPhase      (controller)
    case "Transition"       => new STransition     (controller)
    case "End"              => new SEnd            (controller)
    case "TrueEnd"          => new STrueEnd        (controller)
    case "Apply Effects"    => new ApplyEffects    (controller)
    case "Turn Bifurcation" => new TurnBifurcation (controller)
    case "Regular Turn"     => new RegularTurn     (controller)
    case "Magical Turn"     => new MagicalTurn     (controller)
    case "Enemy Turn"       => new EnemyTurn       (controller)
    case "Equip"            => new Equip           (controller)
    case "Cast"             => new Cast            (controller)
    case "Attack"           => new Attack          (controller)
    case "Turn End"         => new TurnEnd         (controller)
  }

}
