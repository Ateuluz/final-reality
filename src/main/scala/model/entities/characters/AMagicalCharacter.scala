package model.entities.characters

abstract class AMagicalCharacter(
                                  name: String,
                                  hp: Int,
                                  defense: Int,
                                  weight: Int,
                                  mana: Int
                                ) extends ACharacter(name,hp,defense,weight)
                                    with IMagicalCharacter {
  private var _mana: Int = mana
  override def getMana: Int = _mana
  override def setMana(mana: Int): Unit = {
    _mana = constrainMana(mana)
  }
  override def constrainMana(mana: Int): Int = {
    mana match {
      case n if n < 0 => 0
      case _ => mana
    }
  }
}
