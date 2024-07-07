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
abstract class ATeam[T<:IEntity](
                      min: Int,
                      max: Int,
                      initialMembers: Seq[T]
                    ) extends ITeam[T] {
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
  override def getMembers: ArrayBuffer[T] =
    _members

  /** Ateuluz
   *
   * @param member The member to add
   */
  override def addMember(member: T, idx: Int = -1): Unit = {
    var i = idx
    while (i < 0) i += _members.length
    if (idx < 0) i += 1
    if (_members.length >= _maximumMembers) throw new InvalidHandleException("Cannot add member, maximum amount reached.")
    else if (_members.contains(member)) throw new InvalidHandleException("Cannot add member, already in the team.")
    else _members.insert(i,member)
  }

  /** Ateuluz
   *
   * @param oldMember The member to be replaced
   * @param newMember The member to replace
   */
  override def changeMember(
                             oldMember: T,
                             newMember: Option[T]
                           ): Unit = {
    if (newMember.isDefined) {
      val idx = _members.indexOf(oldMember)
      _members -= oldMember
      try {
        this.addMember(newMember.get, idx)
      } catch {
        case _: InvalidHandleException => _members.insert(idx,oldMember)
      }
    }
    else {
      if (_members.length <= _minimumMembers)
        throw new InvalidHandleException("Cannot remove member, minimum amount reached.")
      _members -= oldMember
    }
  }
}
