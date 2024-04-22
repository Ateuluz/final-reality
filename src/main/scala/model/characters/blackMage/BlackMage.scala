package model.characters.blackMage

import model.armament.Weapon
import model.characters.{MagicalCharacter, StaffUser, SwordBearer, WandUser}

class BlackMage (
                  val name: String,
                  var hp: Int,
                  val defense: Int,
                  val weight: Int,
                  var mana: Int,
                  var weapon: Weapon
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
