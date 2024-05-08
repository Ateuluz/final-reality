package model.teams

import model.entities.IEntity

import scala.collection.mutable.ArrayBuffer

/**
 * Trait representing a team and its methods
 */
trait ITeam {
  /**
   *
   * @param member The member to add
   */
  def addMember(member: IEntity): Unit

  /**
   *
   * @return All listed members
   */
  def getMembers: ArrayBuffer[IEntity]

  /**
   *
   * @return The state of the team, true if all members dead
   */
  def isDefeated: Boolean

  /**
   *
   * @param oldMember The member to be replaced
   * @param newMember The member to replace
   */
  def changeMember(oldMember: IEntity, newMember: Option[IEntity]): Unit
}
