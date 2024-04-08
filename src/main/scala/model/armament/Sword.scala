package model.armament

class Sword(
             val name: String,
             val attack: Int,
             val weight: Int,
             var _owner: SwordBearer
           ) extends NonMagicalWeapon {
  override var owner: Character = _owner

  def this(name: String, attack: Int, weight: Int) = {
    this(name, attack, weight, null)
  }
}
