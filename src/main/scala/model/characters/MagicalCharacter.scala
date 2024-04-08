package model.characters

trait MagicalCharacter {
  var mana: Int

  require(mana >= 0, "Negative Mana not allowed")
}
