package controller.states.turnphase.turns

import controller.IGameController
import controller.states.{AGameState, GameStateFactory}

/** Ateuluz
 *
 * @param controller The associated controller for the state
 */
class RegularTurn (
                    controller: IGameController
                  ) extends AGameState(controller) {

  override def step(): Unit = {
    val opt = controller.getInput(
      "Choose an action:" +
        "\n0: Equip Weapon" +
        "\n1: Attack"
    )
    if (opt == "0") {
      controller.state = GameStateFactory.createState("Equip", controller)
    }
    else if (opt == "1") {
      controller.state = GameStateFactory.createState("Attack", controller)
    }
    controller.step()
  }

}
