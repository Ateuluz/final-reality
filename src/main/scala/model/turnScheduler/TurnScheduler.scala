package model.turnScheduler

import model.enemies.Enemy
import model.characters.Character

import scala.collection.mutable.ArrayBuffer

/**
 * Class in charge of managing turns
 */
class TurnScheduler {
  /**
   * Store characters
   */
  val characters: ArrayBuffer[Any] = new ArrayBuffer[Any]()
  /**
   * Store action bars
   */
  val actionBars: ArrayBuffer[Int] = new ArrayBuffer[Int]()

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
   * Add a character and assign it an actionbar
   * @param character
   */
  def addCharacter(character:Any): Unit = {
    this.characters.addOne(character)
    this.actionBars.addOne(0)
  }

  /**
   * Removes a character and its action bar
   * @param character
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
   * @param character
   * @return character's max action bar value
   */
  def getMax(character: Any): Int = {}
}
