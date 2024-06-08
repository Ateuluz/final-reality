package controller

/** Ateuluz
 *
 * An object that simplifies the State handling process
 */
object GameStateFactory {

  def createState(stateType: String, controller: IGameController): IGameState = stateType match {
    case "Start" => new SStart(controller)
    case "Transition" => new STransition(controller)
    case "TurnTaking" => new STurnTaking(controller)
    case "End" => new SEnd(controller)
    case _ => throw new IllegalArgumentException("Unknown state type")
  }

}
