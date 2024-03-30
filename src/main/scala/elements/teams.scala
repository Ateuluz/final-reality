package elements

class Party {
  val characters: Array[Character] = Array.ofDim[Character](3)
}

class Enemies(quantity: Int) {
  val characters: Array[Enemy] = Array.ofDim[Enemy](quantity)
}