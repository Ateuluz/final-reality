package model.entities.characters

trait IMagicalCharacter {
  def getMana: Int
  def setMana(mana: Int): Unit
  def constrainMana(mana: Int): Int
}
