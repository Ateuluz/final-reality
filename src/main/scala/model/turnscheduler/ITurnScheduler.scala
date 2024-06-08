package model.turnscheduler

import model.entities.IEntity
import model.teams.enemies.Enemies
import model.teams.party.Party

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
  def entities: ArrayBuffer[IEntity]

  /** Ateuluz
   *
   * @return The action bars of all linked entities
   */
  def actionBars: ArrayBuffer[Int]

  /** Ateuluz
   *
   * @param character Who's action bar we want
   * @return The entity's action bar
   */
  def actionBarOf(character: IEntity): Int

  /** Ateuluz
   *
   * protected because we don't want anyone to enter, only
   * those in the Party or Enemy team
   *
   * @param entity The entity we want to link
   */
  protected def add(entity:IEntity): Unit

  /** Ateuluz
   *
   * TODO: Make protected
   *
   * @param entity The entity we want to remove
   */
  def remove(entity: IEntity): Unit

  /** Ateuluz
   *
   * @param entity Who's maximum action bar value we want
   * @return Entity's maximum action bar value
   */
  def actionBarMaxOf(entity: IEntity): Int

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
  def charactersFull: ArrayBuffer[IEntity]

  /** Ateuluz
   *
   * @return Entity with best non negative surplus (to take turn)
   */
  def atTurn: IEntity

  /** Ateuluz
   *
   * A method implemented to handle game requirements
   *
   * @return Linked party
   */
  def party: Option[Party]

  /** Ateuluz
   *
   * A method implemented to handle game requirements
   *
   * @return Linked enemy team
   */
  def enemyTeam: Option[Enemies]

  /** Ateuluz
   *
   * Setter for party
   *
   * @param party The team to be linked
   */
  def party_=(party: Party): Unit

  /** Ateuluz
   *
   * Setter for enemyTeam
   *
   * @param enemyTeam The team to be linked
   */
  def enemyTeam_=(enemyTeam: Enemies): Unit

  /** Ateuluz
   *
   * UnSetter for party
   */
  def unbindParty(): Unit

  /** Ateuluz
   *
   * UnSetter for enemy team
   */
  def unbindEnemies(): Unit

  /** Ateuluz
   *
   * A method to be called at the end of every turn
   */
  def removeDead(): Unit

  /** Ateuluz
   *
   * @return Boolean for if end game conditions are met
   */
  def endgame: Boolean
}
