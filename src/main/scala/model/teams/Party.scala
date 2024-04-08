package model.teams

import model.characters.Character

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
