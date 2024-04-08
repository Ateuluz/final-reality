package model.armament

import model.characters.Character

trait Weapon {
  val name: String
  val attack: Int
  val weight: Int
  var owner: Character

  require(attack >= 0, "Negative or zero Attack not allowed")
  require(weight >= 0, "Negative or zero Weight not allowed")
}
