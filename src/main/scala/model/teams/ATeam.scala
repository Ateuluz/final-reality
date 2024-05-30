package model.teams

import exceptions.{InvalidHandleException, Require}
import model.entities.IEntity

import scala.collection.mutable.ArrayBuffer

/** Ateuluz
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
  /** Ateuluz
   *
   * A method to know if a team is defeated
   *
   * A team is considered defeated if all
   * members are dead. If there's at least
   * one member alive, the team isn't defeated.
   *
   * @return Boolean representing if all members are dead
   */
  override def isDefeated: Boolean = { // Somehow it complains when simply returning false on the spot
    var defeated = true
    for (member <- _members){
      if (member.getHp > 0)
          defeated = false
    }
    defeated
  }

  /** Ateuluz
   *
   *  @return All listed members
   */
  override def getMembers: ArrayBuffer[IEntity] =
    _members

  /** Ateuluz
   *
   * @param member The member to add
   */
  override def addMember(member: IEntity): Unit = {
    member match {
      case member if _members.length < _maximumMembers =>
        _members.addOne(member)
      case _ => throw new InvalidHandleException("Cannot add member, maximum amount reached.")
    }
  }

  /** Ateuluz
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
      case _ =>
        if (_members.length <= _minimumMembers)
          throw new InvalidHandleException("Cannot remove member, minimum amount reached.")
        _members -= oldMember
    }
  }
}
