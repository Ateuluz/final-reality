package elements

trait WeaponBearer {
  var weapon: Weapon;
}
trait Character {
  val name: String;
  var hp: Int;
  val defense: Int;
  val weight: Int;
  val magic: Boolean;
}

trait NonMagicalCharacter extends Character {
  val magic: Boolean = false;
}

trait MagicalCharacter extends Character {
  val magic: Boolean = true;
}

class Paladin extends NonMagicalCharacter();

class Warrior extends NonMagicalCharacter();

class Ninja extends NonMagicalCharacter();

class DarkMage extends MagicalCharacter();

class WhiteMage extends MagicalCharacter();