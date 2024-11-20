package model.teams

import model.entities.IEntity

import scala.collection.mutable.ArrayBuffer

/** Ateuluz
 * Trait representing a team and its methods
 */
trait ITeam[T<:IEntity] {
  /** Ateuluz
   *
   * @param member The member to add
   */
  def addMember(member: T, idx: Int): Unit// = -1): Unit

  /** Ateuluz
   *
   * @return All listed members
   */
  def getMembers: ArrayBuffer[T]

  /** Ateuluz
   *
   * @return The state of the team, true if all members dead
   */
  def isDefeated: Boolean

  /** Ateuluz
   *
   * @param oldMember The member to be replaced
   * @param newMember The member to replace
   */
  def changeMember(oldMember: T, newMember: Option[T]): Unit
}
