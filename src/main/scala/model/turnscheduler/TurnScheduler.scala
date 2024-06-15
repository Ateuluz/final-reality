package model.turnscheduler

import exceptions.InvalidHandleException
import model.entities.IEntity
import model.entities.enemies.IEnemy
import model.teams.enemies.Enemies
import model.teams.party.Party

import scala.collection.mutable.ArrayBuffer

/** Ateuluz
 * Class in charge of managing turns
 */
class TurnScheduler extends ITurnScheduler {

  private var _party: Option[Party] = None
  private var _enemyTeam: Option[Enemies] = None

  /** Ateuluz
   *
   *  @return Boolean for linked party state
   */
  override def party: Option[Party] = _party

  /** Ateuluz
   *
   * @return Boolean for linked party state
   */
  override def enemyTeam: Option[Enemies] = _enemyTeam

  /** Ateuluz
   *
   * @param party The party to be linked
   */
  override def party_=(party: Party): Unit =
    if (_party.isDefined)
      throw new InvalidHandleException("A Party is set already")
    else {
      _party = Some(party)
      for (member <- party.getMembers)
        add(member)
    }

  /** Ateuluz
   *
   * @param enemyTeam The enemy team to be linked
   */
  override def enemyTeam_=(enemyTeam: Enemies): Unit =
    if (_enemyTeam.isDefined)
      throw new InvalidHandleException("An Enemy Team is set already")
    else {
      _enemyTeam = Some(enemyTeam)
      for (member <- enemyTeam.getMembers)
        add(member)
    }

  /** Ateuluz
   *
   * Remove all members of the Party
   */
  override def unbindParty(): Unit =
    if (_party.isEmpty)
      throw new InvalidHandleException("There is no linked Party")
    else {
      //val clone = ArrayBuffer() ++= _party.get.getMembers
      for (member <- _party.get.getMembers.clone())
        try remove(member)
        catch {
          // Perhaps the character was already eliminated if hp got to zero
          case _: IndexOutOfBoundsException => println("Character already removed")
        }
      _party = None
    }

  /** Ateuluz
   *
   * Remove all members of the Enemy Team
   */
   override def unbindEnemies(): Unit =
     if (_enemyTeam.isEmpty)
       throw new InvalidHandleException("There is no linked Enemy Team")
     else {
       //val clone = ArrayBuffer() ++= _enemyTeam.get.getMembers
       for (member <- _enemyTeam.get.getMembers.clone())
         try remove(member)
         catch {
           // Perhaps the enemy was already eliminated if hp got to zero
           case _: IndexOutOfBoundsException => println("Enemy already removed")
         }
       _enemyTeam = None
     }

  /** Ateuluz
   * Store characters
   */
  private val _entities: ArrayBuffer[IEntity] = new ArrayBuffer[IEntity]()
  /** Ateuluz
   * Store action bars
   */
  private val _actionBars: ArrayBuffer[Int] = new ArrayBuffer[Int]()

  /** Ateuluz
   * Get all stored characters
   *
   * @return listed characters
   */
  override def entities: ArrayBuffer[IEntity] = _entities

  /** Ateuluz
   * Get all stored action bars
   *
   * @return listed action bars
   */
  override def actionBars: ArrayBuffer[Int] = _actionBars

  /** Ateuluz
   *
   * TODO: Perhaps an exception here would be nice
   *
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
  override def actionBarOf(character: IEntity): Int = {
    val characterIndex = _entities.indexOf(character)
    if (characterIndex != -1)
      _actionBars(characterIndex)
    else
      -1
  }

  /** Ateuluz
   * Add a character and assign it an actionbar
   *
   * @param character New character to store
   */
  override protected def add(character: IEntity): Unit = {
    _entities.addOne(character)
    _actionBars.addOne(0)
  }

  /** Ateuluz
   *
   * TODO: work only upon death or Team removal
   *
   * Removes a character and its action bar
   *
   * @param character Character to be removed along with action bar
   */
  override def remove(character: IEntity): Unit = {
    val idx: Int = _entities.indexOf(character)
    _entities.remove(idx)
    _actionBars.remove(idx)
  }

  /** Ateuluz
   *
   * Remove all dead linked entities
   */
  override def removeDead(): Unit =
    for (ent <- _entities.clone()) if (ent.getHp == 0) remove(ent)

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
  override def actionBarMaxOf(character: IEntity): Int =
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
    for (i <- _actionBars.indices) _actionBars(i) += k
  }

  /** Ateuluz
   *
   * TODO: Perhaps an exception...
   *
   * Public while no game implementation is
   * specified.
   *
   * Reset a character's action bar
   *
   * @param character Who's action bar we want to be reset
   */
  override def reset(character: IEntity): Unit = {
    val characterIndex = _entities.indexOf(character)
    if (characterIndex != -1)
      _actionBars(characterIndex) = 0
    else
      println(s"Character not found in this.characters : reset $character")
  }

  /** Ateuluz
   * Return if a character is ready to take action
   *
   * @param character The character to evaluate
   * @return action bar at/over max
   */
  private def isFull(character: IEntity): Boolean = {
    val characterIndex = _entities.indexOf(character)
    // We are sure character has index for this is a private method
    _actionBars(characterIndex) >= this.actionBarMaxOf(character)
  }

  /** Ateuluz
   * Get descending array of all characters with action bar at/over max
   *
   * @return characters ready to take action
   */
  override def charactersFull: ArrayBuffer[IEntity] = {
    val auxArr: ArrayBuffer[IEntity] = ArrayBuffer[IEntity]()
    for (char <- _entities)
      // if (this.getActionBarMax(char) <= this.getActionBar(char)) {
      //   auxArr.addOne(char)
      // }
      if (isFull(char)) auxArr.addOne(char)

    def auxFun(char: IEntity): Int =
      this.actionBarMaxOf(char) - this.actionBarOf(char)

    auxArr.sortBy(auxFun)
  }

  /** Ateuluz
   * Get character to whom the current turn belongs to
   *
   * @return character to take current turn
   */
  override def atTurn: IEntity = charactersFull(0)

  /** Ateuluz
   *
   *  @return Boolean for if end game conditions are met
   */
  override def endgame: Boolean =
    if (_party.isEmpty || _enemyTeam.isEmpty)
      false
    else if (_party.get.isDefeated || _enemyTeam.get.isDefeated)
      true
    else
      false
}
