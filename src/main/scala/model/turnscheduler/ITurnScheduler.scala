package model.turnscheduler

import model.entities.IEntity

import scala.collection.mutable.ArrayBuffer

/** Ateuluz
 * Trait that defines what a turn scheduler is
 * TurnScheduler manages Entities and their turns
 */
trait ITurnScheduler {
  /** Ateuluz
   *
   * @return All the entities taking turns in game
   */
  def getCharacters: ArrayBuffer[IEntity]

  /** Ateuluz
   *
   * @return The action bars of all linked entities
   */
  def getActionBars: ArrayBuffer[Int]

  /** Ateuluz
   *
   * @param character Who's action bar we want
   * @return The entity's action bar
   */
  def getActionBar(character: IEntity): Int

  /** Ateuluz
   *
   * @param character The entity we want to link
   */
  def addCharacter(character:IEntity): Unit

  /** Ateuluz
   *
   * @param character The entity we want to remove
   */
  def removeCharacter(character: IEntity): Unit

  /** Ateuluz
   *
   * @param character Who's maximum action bar value we want
   * @return Entity's maximum action bar value
   */
  def getActionBarMax(character: IEntity): Int

  /** Ateuluz
   *
   * @param k The action bar raise constant
   */
  def raiseActionBars(k: Int): Unit

  /** Ateuluz
   *
   * @param character Who's action bar we want to reset
   */
  def reset(character: IEntity): Unit

  /** Ateuluz
   *
   * @return All linked entities with a full action bar, sorted by desc surplus
   */
  def getCharactersFull: ArrayBuffer[IEntity]

  /** Ateuluz
   *
   * @return Entity with best non negative surplus (to take turn)
   */
  def getAtTurn: IEntity
}
