package controller.states.turnphase

import controller.IGameController
import controller.states.{AGameState, GameStateFactory}
import model.effects.IEffect

import scala.collection.mutable.ArrayBuffer

/** Ateuluz
 *
 * @param controller The associated controller for the state
 */
class ApplyEffects (
                      controller: IGameController
                    ) extends AGameState(controller) {

  /** Ateuluz
   *
   * @param effects List of effects
   * @return A list of effects
   */
  private def getEffectsList(effects: ArrayBuffer[_ <: IEffect]): String = {
    effects.zipWithIndex.map { case (effect, index) =>
      s"  ${index + 1}: ${effect.getClass.getSimpleName}"
    }.mkString("\n")
  }

  /** Ateuluz
   *
   * Game flow
   */
  override def step(): Unit = {
    val ent = controller.turnScheduler.atTurn
    if (ent.effects.nonEmpty) {
      println(s"[Effects] ${ent.getName} is under:\n${getEffectsList(ent.effects)}")
      ent.effectsApply()
      println(s"[Status Update] ${ent.getName} [HP: ${ent.getHp}/${ent.getHpMax}]")
    } else println("Under no effects!\n")
    if (ent.actionAble) {
      println("Moving to turn!")
      controller.state = GameStateFactory.createState("Turn Bifurcation",controller)
    } else {
      println("Cannot take action!")
      controller.state = GameStateFactory.createState("Turn End", controller)
    }

    controller.step()
  }

}
