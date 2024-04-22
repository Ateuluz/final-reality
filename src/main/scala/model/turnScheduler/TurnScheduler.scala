package model.turnScheduler

import model.entities.characters.Character
import model.entities.enemies.enemy.Enemy

import scala.collection.mutable.ArrayBuffer

/**
 * Class in charge of managing turns
 */
class TurnScheduler {
  /**
   * Store characters
   */
  private val characters: ArrayBuffer[Any] = new ArrayBuffer[Any]()
  /**
   * Store action bars
   */
  private val actionBars: ArrayBuffer[Int] = new ArrayBuffer[Int]()

  /**
   * Get all stored characters
   * @return listed characters
   */
  def getCharacters: ArrayBuffer[Any] = {
    this.characters
  }

  /**
   * Get all stored action bars
   * @return listed action bars
   */
  def getActionBars: ArrayBuffer[Int] = {
    this.actionBars
  }

  /**
   * Get the action bar of a single character if stored else -1
   * @param character Who's action bar we want
   * @return action bar
   */
  def getActionBar(character: Any): Int = {
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
  def addCharacter(character:Any): Unit = {
    this.characters.addOne(character)
    this.actionBars.addOne(0)
  }

  /**
   * Removes a character and its action bar
   * @param character Character to be removed along with action bar
   */
  def removeCharacter(character: Any): Unit = {
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
  def getActionBarMax(character: Any): Int = {
    character match {
      case char: Character => char.weight + (char.weapon.weight.toFloat / 2).ceil.toInt

      case char: Enemy => char.weight

      case _ => throw new IllegalArgumentException("Invalid class")
      //Perhaps 0, might change
    }
  }

  /**
   * Raise all action bars by a constant k
   * @param k constant
   */
  def raiseActionBars(k: Int): Unit = {
    for (i <- this.actionBars.indices) {
      this.actionBars(i) += k
    }
  }

  /**
   * Reset a character's action bar
   * @param character Who's action bar we want to be reset
   */
  def reset(character: Any): Unit = {
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
  def isFull(character: Any): Boolean = {
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
  def getCharactersFull: ArrayBuffer[Any] = {
    val auxArr: ArrayBuffer[Any] = ArrayBuffer[Any]()
    for (char <- this.characters) {
      if (this.getActionBarMax(char) <= this.getActionBar(char)) {
        auxArr.addOne(char)
      }
    }
    def aux(char: Any): Int = {
      this.getActionBarMax(char) - this.getActionBar(char)
    }
    auxArr.sortBy(aux)
  }

  /**
   * Get character to whom the current turn belongs to
   * @return character to take current turn
   */
  def getAtTurn: Any = {
    getCharactersFull(0)
  }
}
