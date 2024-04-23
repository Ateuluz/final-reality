package model.teams.party

import model.entities.characters.ICharacter

class Party {
  private val characters: Array[ICharacter] = Array.ofDim[ICharacter](3)

  def getCharacters: Array[ICharacter] = {
    this.characters
  }

  def addCharacter(character: ICharacter): Unit = {
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
