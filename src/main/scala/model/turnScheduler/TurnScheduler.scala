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

  def addCharacter(character:Any): Unit = {
    this.characters.addOne(character)
  }
}
