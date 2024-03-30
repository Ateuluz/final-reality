package elements

import scala.util.control.Breaks.break

class Party {
  val characters: Array[Character] = Array.ofDim[Character](3)

  def addCharacter(character: Character): Unit = {
    for (slot <- 0 until this.characters.length){
      if (this.characters(slot) == null) {
        this.characters(slot) = character
        return
      }
    }
  }

  def isDefeated: Boolean = {
    (this.characters(0).hp == 0) && (this.characters(1).hp == 0) && (this.characters(2).hp == 0)
  }
}

class Enemies(quantity: Int) {
  val characters: Array[Enemy] = Array.ofDim[Enemy](quantity)

  def addEnemy(enemy: Enemy): Unit = {
    for (slot <- 0 until this.characters.length){
      if (this.characters(slot) == null) {
        this.characters(slot) = enemy
        return
      }
    }
  }
}