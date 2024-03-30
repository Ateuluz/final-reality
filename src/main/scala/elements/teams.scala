package elements

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
}

class Enemies(quantity: Int) {
  val characters: Array[Enemy] = Array.ofDim[Enemy](quantity)
}