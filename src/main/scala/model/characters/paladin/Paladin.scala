package model.characters.paladin

import model.armament.Weapon
import model.characters.{AxeBearer, NonMagicalCharacter, SwordBearer}

class Paladin (
                val name: String,
                var hp: Int,
                val defense: Int,
                val weight: Int,
                var weapon: Weapon
              ) extends NonMagicalCharacter() with SwordBearer with AxeBearer {

  def this(
            name: String,
            hp: Int,
            defense: Int,
            weight: Int,
          ) = {
    this(name, hp, defense, weight, null)
  }
}
