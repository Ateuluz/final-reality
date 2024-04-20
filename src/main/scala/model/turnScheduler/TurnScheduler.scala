package model.turnScheduler

import scala.collection.mutable.ArrayBuffer

class TurnScheduler {
  val characters: ArrayBuffer[Any] = new ArrayBuffer[Any]()

  def getCharacters: ArrayBuffer[Any] = {
    this.characters
  }
}
