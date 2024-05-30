package model.turnscheduler

import model.entities.IEntity
import model.entities.playablecharacters.ICharacter
import model.entities.enemies.enemy.Enemy

import scala.collection.mutable.ArrayBuffer

/** Ateuluz
 * Class in charge of managing turns
 */
class TurnScheduler extends ITurnScheduler {
  /** Ateuluz
   * Store characters
   */
  private val _characters: ArrayBuffer[IEntity] = new ArrayBuffer[IEntity]()
  /** Ateuluz
   * Store action bars
   */
  private val _actionBars: ArrayBuffer[Int] = new ArrayBuffer[Int]()

  /** Ateuluz
   * Get all stored characters
   *
   * @return listed characters
   */
  override def getCharacters: ArrayBuffer[IEntity] = {
    _characters
  }

  /** Ateuluz
   * Get all stored action bars
   *
   * @return listed action bars
   */
  override def getActionBars: ArrayBuffer[Int] = {
    _actionBars
  }

  /** Ateuluz
   * Defined public, since we might want to use a certain
   * game mode in which we raise the action bar in such
   * a way all characters attack once for every time the
   * boss attacks or other options.
   * Might replace later
   *
   * Get the action bar of a single character if stored else -1
   *
   * @param character Who's action bar we want
   * @return action bar
   */
  override def getActionBar(character: IEntity): Int = {
    val characterIndex = _characters.indexOf(character)
    if (characterIndex != -1) {
      _actionBars(characterIndex)
    } else {
      -1
    }
  }

  /** Ateuluz
   * Add a character and assign it an actionbar
   *
   * @param character New character to store
   */
  override def addCharacter(character: IEntity): Unit = {
    _characters.addOne(character)
    _actionBars.addOne(0)
  }

  /** Ateuluz
   * Removes a character and its action bar
   *
   * @param character Character to be removed along with action bar
   */
  override def removeCharacter(character: IEntity): Unit = {
    val idx: Int = _characters.indexOf(character)
    _characters.remove(idx)
    _actionBars.remove(idx)
  }

  /** Ateuluz
   * Defined public, since we might want to use a certain
   * game mode in which we raise the action bar in such
   * a way all characters attack once for every time the
   * boss attacks or other options.
   * Might replace later
   *
   * Return action bar max value of a given character
   * Should there be a decimal max, it'll return the ceil
   *
   * @param character Who's max action bar value we want
   * @return character's max action bar value
   */
  override def getActionBarMax(character: IEntity): Int =
    character.getRelevantWeight

  /** Ateuluz
   * Public while no game implementation is
   * specified.
   *
   * Raise all action bars by a constant k
   *
   * @param k constant
   */
  override def raiseActionBars(k: Int): Unit = {
    for (i <- _actionBars.indices) {
      _actionBars(i) += k
    }
  }

  /** Ateuluz
   * Public while no game implementation is
   * specified.
   *
   * Reset a character's action bar
   *
   * @param character Who's action bar we want to be reset
   */
  override def reset(character: IEntity): Unit = {
    val characterIndex = _characters.indexOf(character)
    if (characterIndex != -1) {
      _actionBars(characterIndex) = 0
    } else {
      println(s"Character not found in this.characters : reset $character")
    }
  }

  /** Ateuluz
   * Return if a character is ready to take action
   *
   * @param character The character to evaluate
   * @return action bar at/over max
   */
  private def isFull(character: IEntity): Boolean = {
    val characterIndex = _characters.indexOf(character)
    // We are sure character has index for this is a private method
    _actionBars(characterIndex) >= this.getActionBarMax(character)
  }

  /** Ateuluz
   * Get descending array of all characters with action bar at/over max
   *
   * @return characters ready to take action
   */
  override def getCharactersFull: ArrayBuffer[IEntity] = {
    val auxArr: ArrayBuffer[IEntity] = ArrayBuffer[IEntity]()
    for (char <- _characters) {
      // if (this.getActionBarMax(char) <= this.getActionBar(char)) {
      //   auxArr.addOne(char)
      // }
      if (isFull(char)) auxArr.addOne(char)
    }

    def auxFun(char: IEntity): Int = {
      this.getActionBarMax(char) - this.getActionBar(char)
    }

    auxArr.sortBy(auxFun)
  }


  /** Ateuluz
   * Get character to whom the current turn belongs to
   *
   * @return character to take current turn
   */
  override def getAtTurn: IEntity = {
    getCharactersFull(0)
  }
}
