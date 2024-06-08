package controller

import model.entities.IEntity
import model.entities.enemies.IEnemy

class STurnTaking(
                   controller: IGameController
                 ) extends AGameState(controller) {

  //private def applyEffects(): Unit = ???

  /** Ateuluz
   *
   * Method in charge of action logic handling
   *
   * @param entity Entity to take action
   */
  private def takeAction(entity: IEntity): Unit = {
    //<editor-fold desc="Action logic">
    // For now just attack
    // By invariant we know there's enough entities to do something
    if (controller.turnScheduler.enemyTeam.get.getMembers.contains(entity)) {
      val target = controller.turnScheduler.party.get.getMembers.filter(
        controller.turnScheduler.entities.contains(_)
      )(0)
      entity.attack(target)
    } else {
      val target = controller.turnScheduler.enemyTeam.get.getMembers.filter(
        controller.turnScheduler.entities.contains(_)
      )(0)
      entity.attack(target)
    }
    //</editor-fold>
  }

  /** Ateuluz
   *
   * Relevant end game decisions checked here
   */
  override def step(): Unit = {
    /*for (entity <- controller.turnScheduler.charactersFull) takeAction(entity)*/
    if (controller.turnScheduler.charactersFull.nonEmpty)
      takeAction(controller.turnScheduler.atTurn)
    loop()
  }

  /** Ateuluz
   *
   * Method to get to the main game loop
   */
  override def loop(): Unit = {
    println("Turn Taken! Looping over!")

    controller.state = GameStateFactory.createState("Transition", controller)
  }
}
