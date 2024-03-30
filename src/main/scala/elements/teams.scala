package elements

class Party {
  val characters: Array[Character] = Array.ofDim(3)
}

class Enemies(quantity: Int) {
  val characters: Array[Character] = Array.ofDim(quantity)
}