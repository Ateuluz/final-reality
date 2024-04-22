package model.teams.party

import model.characters.Character

class Party {
  private val characters: Array[Character] = Array.ofDim[Character](3)

  def getCharacters: Array[Character] = {
    this.characters
  }

  def addCharacter(character: Character): Unit = {
    for (slot <- this.characters.indices){
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
