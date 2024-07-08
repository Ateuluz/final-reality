package controller.states.turnphase.actions

import controller.IGameController
import controller.states.{AGameState, GameStateFactory}
import exceptions.{InvalidActionException, InvalidHolderException}

/** Ateuluz
 *
 * @param controller The associated controller for the state
 */
class Equip (
              controller: IGameController
            ) extends AGameState(controller) {

  override def step(): Unit = {
    val opt = controller.getInput(
      s"Choose a weapon:" +
        s"\n1: ${controller.weaponInventory(1-1).getName} ${controller.weaponInventory(1-1).getClass.getSimpleName}" +
        s"\n2: ${controller.weaponInventory(2-1).getName} ${controller.weaponInventory(2-1).getClass.getSimpleName}" +
        s"\n3: ${controller.weaponInventory(3-1).getName} ${controller.weaponInventory(3-1).getClass.getSimpleName}" +
        s"\n4: ${controller.weaponInventory(4-1).getName} ${controller.weaponInventory(4-1).getClass.getSimpleName}" +
        s"\n5: ${controller.weaponInventory(5-1).getName} ${controller.weaponInventory(5-1).getClass.getSimpleName}"
    )
    val char = controller.turnScheduler.atTurnCharacter
    val wp = char.getWeapon.get
    try {
      if (opt == "1" || opt == "2" || opt == "3" || opt == "4" || opt == "5") {
        controller.turnScheduler.atTurnCharacter.equip(controller.weaponInventory(opt.toInt-1))
      }
    } catch {
      case e: InvalidActionException =>
        println(s"${e.getMessage}")
        if (char.getWeapon.isEmpty) char.equip(wp)
        controller.state = GameStateFactory.createState("Turn Bifurcation", controller)
      case e: InvalidHolderException =>
        println(s"${e.getMessage}")
        if (char.getWeapon.isEmpty) char.equip(wp)
        controller.state = GameStateFactory.createState("Turn Bifurcation", controller)
    }
    if (wp != controller.turnScheduler.atTurnCharacter.getWeapon.get) {
      if (wp.getWeight < controller.turnScheduler.atTurnCharacter.getWeapon.get.getWeight) {
        println("The weapon is too heavy! Skipping turn")
        controller.state = GameStateFactory.createState("Turn End", controller)
      }
      else {
        println("This weapon is not too heavy! You can still take action!")
        controller.state = GameStateFactory.createState("Turn Bifurcation", controller)
      }
    }
    controller.step()
  }

}
