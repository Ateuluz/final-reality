package model.turnScheduler

import model.entities.IEntity
import model.entities.characters.ICharacter
import model.entities.enemies.enemy.Enemy

import scala.collection.mutable.ArrayBuffer

trait ITurnScheduler {
  def getCharacters: ArrayBuffer[IEntity]
  def getActionBars: ArrayBuffer[Int]
  def getActionBar(character: IEntity): Int
  def addCharacter(character:IEntity): Unit
  def removeCharacter(character: IEntity): Unit
  def getActionBarMax(character: IEntity): Int
  def raiseActionBars(k: Int): Unit
  def reset(character: IEntity): Unit
  def isFull(character: IEntity): Boolean
  def getCharactersFull: ArrayBuffer[IEntity]
  def getAtTurn: IEntity
}
