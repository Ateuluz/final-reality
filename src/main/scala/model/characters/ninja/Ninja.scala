package model.characters.ninja

import model.armament.Weapon
import model.characters.{BowBearer, NonMagicalCharacter, SwordBearer, WandUser}

class Ninja (
              val name: String,
              var hp: Int,
              val defense: Int,
              val weight: Int,
              var weapon: Weapon
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
