package controller.states

import controller.IGameController
import exceptions.InvalidStateException
import model.entities.IEntity
import model.entities.enemies.IEnemy
import model.spells.ISpell

import scala.collection.mutable.ArrayBuffer

/** Ateuluz
 *
 * An abstract class for defining Game states and their methods
 *
 * @param controller The associated controller for the state
 */
abstract class AGameState (
                            val controller: IGameController
                          ) extends IGameState {

  /** Ateuluz
   *
   * To improve readability
   */
  private def raiseException(): Unit = throw new InvalidStateException("This State cannot handle the intended action")

  /** Ateuluz
   *
   * Step method to handle game flow
   */
  override def step(): Unit = raiseException()

  /** Ateuluz
   *
   * @param entities Potential targets
   * @return A string to pass as prompt
   */
  protected def mapEntitiesToIndexedString(entities: ArrayBuffer[_ <: IEntity]): String = {
    entities.zipWithIndex.map { case (entity, index) =>
      if (entity.getHp == 0)
        s"${index + 1}: ${entity.getName} [Dead]"
      else
        s"${index + 1}: ${entity.getName} [HP: ${entity.getHp}/${entity.getHpMax}]"

    }.mkString("\n")
  }

  /** Ateuluz
   *
   * @param entities Where to choose from
   * @return Target
   */
  protected def chooseTargetGeneric(entities: ArrayBuffer[_ <: IEntity]): IEntity = {
    val entitiesList = mapEntitiesToIndexedString(entities)
    val prompt = s"Choose your target:\n$entitiesList"
    val opt = controller.getInput(prompt)
    try {
      val index = opt.toInt - 1
      if (index >= 0 && index < entities.length) {
        entities(index)
      } else {
        println("Invalid choice. Please choose a valid target number.")
        chooseTargetGeneric(entities)
      }
    } catch {
      case _: NumberFormatException =>
        println("Invalid input. Please enter a number.")
        chooseTargetGeneric(entities)
    }
  }

  protected def chooseSpell(spells: ArrayBuffer[ISpell]): Int = {
    val spellsList = spells.zipWithIndex.map { case (spell, index) =>
      s"${index + 1}: ${spell.getClass.getSimpleName}"
    }.mkString("\n")

    val prompt = s"Choose a spell:\n$spellsList"
    val opt = controller.getInput(prompt)

    try {
      val spellID = opt.toInt - 1
      if (spellID >= 0 && spellID < spells.length) {
        spellID
      } else {
        println("Invalid choice. Please choose a valid spell number.")
        chooseSpell(spells)
      }
    } catch {
      case _: NumberFormatException =>
        println("Invalid input. Please enter a number.")
        chooseSpell(spells)
    }
  }

}
