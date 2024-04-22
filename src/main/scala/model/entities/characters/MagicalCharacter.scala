package model.entities.characters

trait MagicalCharacter {
  var mana: Int

  require(mana >= 0, "Negative Mana not allowed")
}
