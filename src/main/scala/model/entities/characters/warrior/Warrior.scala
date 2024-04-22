package model.entities.characters.warrior

import model.armament.Weapon
import model.entities.characters.{AxeBearer, BowBearer, NonMagicalCharacter, SwordBearer}

class Warrior (
                val name: String,
                var hp: Int,
                val defense: Int,
                val weight: Int,
                var weapon: Weapon
              ) extends NonMagicalCharacter() with SwordBearer with AxeBearer with BowBearer{
  def this(
            name: String,
            hp: Int,
            defense: Int,
            weight: Int,
          ) = {
    this(name, hp, defense, weight, null)
  }
}
