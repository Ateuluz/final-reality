package model.characters

import model.armament.Weapon

class BlackMage (
                  val name: String,
                  var hp: Int,
                  val defense: Int,
                  val weight: Int,
                  var mana: Int,
                  var weapon: Weapon = null
                ) extends MagicalCharacter() with SwordBearer with WandUser with StaffUser {
  def this(
            name: String,
            hp: Int,
            defense: Int,
            weight: Int,
            mana: Int
          ) = {
    this(name, hp, defense, weight, mana, null)
  }
}
