package model.teams

import model.entities.IEntity

import scala.collection.mutable.ArrayBuffer

trait ITeam {
  def addMember(member: IEntity): Unit
  def getMembers: ArrayBuffer[IEntity]
  def isDefeated: Boolean
}
