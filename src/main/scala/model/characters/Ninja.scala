package model.characters

import model.armament.Weapon

class Ninja (
              val name: String,
              var hp: Int,
              val defense: Int,
              val weight: Int,
              var weapon: Weapon = null
            ) extends NonMagicalCharacter() with SwordBearer with BowBearer with WandUser {
  def this(
            name: String,
            hp: Int,
            defense: Int,
            weight: Int,
          ) = {
    this(name, hp, defense, weight, null)
  }
}
