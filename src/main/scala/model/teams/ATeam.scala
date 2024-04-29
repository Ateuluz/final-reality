package model.teams

import exceptions.Require
import model.entities.IEntity

import scala.collection.mutable.ArrayBuffer

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
  override def getMembers: ArrayBuffer[IEntity] =
    _members
  override def isDefeated: Boolean = {
    for (member <- _members){
      if (member.getHp > 0)
          return false
    }
    true
  }
  override def addMember(member: IEntity): Unit = {
    member match {
      case member if _members.length < _maximumMembers =>
        _members.addOne(member)
      case _ => println("Members are full")
    }
  }
}
