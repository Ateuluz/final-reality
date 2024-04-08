package model.characters

import model.armament.Weapon

class Paladin (
                val name: String,
                var hp: Int,
                val defense: Int,
                val weight: Int,
                var weapon: Weapon = null
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
