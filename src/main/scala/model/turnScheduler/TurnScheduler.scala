package model.turnScheduler

import model.entities.IEntity
import model.entities.characters.ICharacter
import model.entities.enemies.enemy.Enemy

import scala.collection.mutable.ArrayBuffer

/**
 * Class in charge of managing turns
 */
class TurnScheduler extends ITurnScheduler {
  /**
   * Store characters
   */
  private val characters: ArrayBuffer[IEntity] = new ArrayBuffer[IEntity]()
  /**
   * Store action bars
   */
  private val actionBars: ArrayBuffer[Int] = new ArrayBuffer[Int]()

  /**
   * Get all stored characters
   * @return listed characters
   */
  override def getCharacters: ArrayBuffer[IEntity] = {
    this.characters
  }

  /**
   * Get all stored action bars
   * @return listed action bars
   */
  override def getActionBars: ArrayBuffer[Int] = {
    this.actionBars
  }

  /**
   * Get the action bar of a single character if stored else -1
   * @param character Who's action bar we want
   * @return action bar
   */
  override def getActionBar(character: IEntity): Int = {
    val characterIndex = this.characters.indexOf(character)
    if (characterIndex != -1) {
      this.actionBars(characterIndex)
    } else {
      -1
    }
  }

  /**
   * Add a character and assign it an actionbar
   * @param character New character to store
   */
  override def addCharacter(character:IEntity): Unit = {
    this.characters.addOne(character)
    this.actionBars.addOne(0)
  }

  /**
   * Removes a character and its action bar
   * @param character Character to be removed along with action bar
   */
  override def removeCharacter(character: IEntity): Unit = {
    val idx: Int = this.characters.indexOf(character)
    if (idx >= 0) {
      this.characters.remove(idx)
      this.actionBars.remove(idx)
    }
  }

  /**
   * Return action bar max value of a given character
   * Should there be a decimal max, it'll return the ceil
   * @param character Who's max action bar value we want
   * @return character's max action bar value
   */
  override def getActionBarMax(character: IEntity): Int = {
    character match {
      case char: ICharacter =>
        char.getWeapon match {
          case Some(weapon) => char.getWeight + (weapon.getWeight.toFloat / 2).ceil.toInt
          case _ => 0
        }

      case char: Enemy => char.getWeight
    }
  }

  /**
   * Raise all action bars by a constant k
   * @param k constant
   */
  override def raiseActionBars(k: Int): Unit = {
    for (i <- this.actionBars.indices) {
      this.actionBars(i) += k
    }
  }

  /**
   * Reset a character's action bar
   * @param character Who's action bar we want to be reset
   */
  override def reset(character: IEntity): Unit = {
    val characterIndex = this.characters.indexOf(character)
    if (characterIndex != -1) {
      this.actionBars(characterIndex) = 0
    } else {
      println(s"Character not found in this.characters : reset $character")
    }
  }

  /**
   * Return if a character is ready to take action
   * @param character The character to evaluate
   * @return action bar at/over max
   */
  override def isFull(character: IEntity): Boolean = {
    val characterIndex = this.characters.indexOf(character)
    if (characterIndex != -1) {
      this.actionBars(characterIndex) >= this.getActionBarMax(character)
    } else {
      false
    }
  }

  /**
   * Get descending array of all characters with action bar at/over max
   * @return characters ready to take action
   */
  override def getCharactersFull: ArrayBuffer[IEntity] = {
    val auxArr: ArrayBuffer[IEntity] = ArrayBuffer[IEntity]()
    for (char <- this.characters) {
      if (this.getActionBarMax(char) <= this.getActionBar(char)) {
        auxArr.addOne(char)
      }
    }
    def auxFun(char: IEntity): Int = {
      this.getActionBarMax(char) - this.getActionBar(char)
    }
    auxArr.sortBy(auxFun)
  }

  /**
   * Get character to whom the current turn belongs to
   * @return character to take current turn
   */
  override def getAtTurn: IEntity = {
    getCharactersFull(0)
  }
}
