package model.turnScheduler

import model.enemies.Enemy
import model.characters.Character

import scala.collection.mutable.ArrayBuffer

class TurnScheduler {
  val characters: ArrayBuffer[Any] = new ArrayBuffer[Any]()
  val actionBars: ArrayBuffer[Int] = new ArrayBuffer[Int]()

  def getCharacters: ArrayBuffer[Any] = {
    this.characters
  }

  def getActionBars: ArrayBuffer[Int] = {
    this.actionBars
  }

  def addCharacter(character:Any): Unit = {
    this.characters.addOne(character)
    this.actionBars.addOne(0)
  }

  def removeCharacter(character: Any): Any = {
    val idx: Int = this.characters.indexOf(character)
    if (idx >= 0) {
      this.characters.remove(idx)
      this.actionBars.remove(idx)
    }
  }
}
