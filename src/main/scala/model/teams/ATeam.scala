package model.teams

import exceptions.Require
import model.entities.IEntity

import scala.collection.mutable.ArrayBuffer

/**
 *
 * @param min The minimum amount of members
 * @param max The maximum amount of members
 * @param initialMembers Sequence of members to start with, within boundaries
 */
abstract class ATeam(
                      min: Int,
                      max: Int,
                      initialMembers: Seq[IEntity]
                    ) extends ITeam {
  private val _members = ArrayBuffer(initialMembers: _*)
  private val _minimumMembers = min
  private val _maximumMembers = max
  Require.Stat(
    _members.length,
    "initialMembers"
  ) in (
    _minimumMembers
      to _maximumMembers
    )
  /** A method to know if a team is defeated
   *
   * A team is considered defeated if all
   * members are dead. If there's at least
   * one member alive, the team isn't defeated.
   *
   * @return Boolean representing if all members are dead
   */
  override def isDefeated: Boolean = {
    for (member <- _members){
      if (member.getHp > 0)
          return false
    }
    true
  }

  /**
   *
   *  @return All listed members
   */
  override def getMembers: ArrayBuffer[IEntity] =
    _members

  /**
   *
   * @param member The member to add
   */
  override def addMember(member: IEntity): Unit = {
    member match {
      case member if _members.length < _maximumMembers =>
        _members.addOne(member)
      case _ => println("Members are full")
    }
  }

  /**
   *
   * @param oldMember The member to be replaced
   * @param newMember The member to replace
   */
  override def changeMember(
                             oldMember: IEntity,
                             newMember: Option[IEntity]
                           ): Unit = {
    newMember match {
      case Some(member) =>
        _members -= oldMember
        this.addMember(member)
      case None =>
        if (_members.length > _minimumMembers)
          _members -= oldMember
    }
  }
}