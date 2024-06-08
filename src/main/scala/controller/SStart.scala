package controller

import exceptions.InvalidHandleException

/**
 *
 * @param controller The controller to which we associate the state
 */
class SStart(
              controller: IGameController
            ) extends AGameState(controller) {

  /** Ateuluz
   *
   *
   */
  override def step(): Unit = {
    //<editor-fold desc="All decisions before battle are handled here">
    /*Pre game logic*/
    //</editor-fold>
    loop() // So far this is the only relevant thing
  }

  /** Ateuluz
   *
   * Method to get to the main game loop
   * Relevant end game decisions checked here
   */
  override def loop(): Unit = {

    val entities = controller.turnScheduler.entities

    if (controller.turnScheduler.party.isEmpty)
      throw new InvalidHandleException("Cannot start the game, missing Playable Characters")
    if (controller.turnScheduler.enemyTeam.isEmpty)
      throw new InvalidHandleException("Cannot start the game, missing Enemies")

    val pMem = controller.turnScheduler.party.get.getMembers
    val eMem = controller.turnScheduler.enemyTeam.get.getMembers

    if (!pMem.exists(entity => entities.contains(entity)))
      throw new InvalidHandleException("Cannot start the game, all Playable Characters removed")
    if (!eMem.exists(entity => entities.contains(entity)))
      throw new InvalidHandleException("Cannot start the game, all Enemies removed")


    println("Game has begun!")

    controller.state = GameStateFactory.createState("Transition", controller)
  }
}
