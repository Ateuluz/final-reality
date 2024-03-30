package elements

import scala.util.control.Breaks.break

class Party {
  val characters: Array[Character] = Array.ofDim[Character](3)

  def addCharacter(character: Character): Unit = {
    if (this.characters(0) == null) {
      this.characters(0) = character
    } else if (this.characters(1) == null) {
      this.characters(1) = character
    } else if(this.characters(2) == null) {
      this.characters(2) = character
    }
  }

  def isDefeated: Boolean = {
    (this.characters(0).hp == 0) && (this.characters(1).hp == 0) && (this.characters(2).hp == 0)
  }
}

class Enemies(quantity: Int) {
  val characters: Array[Enemy] = Array.ofDim[Enemy](quantity)

  def addEnemy(enemy: Enemy): Unit = {
    for (slot <- 0 to this.characters.length){
      if (this.characters(slot) == null) {
        this.characters(slot) = enemy
        break
      }
    }
  }
}