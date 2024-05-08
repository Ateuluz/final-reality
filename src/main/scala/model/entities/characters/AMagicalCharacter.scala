package model.entities.characters

import exceptions.Require

/**
 *
 * @param name    The name of the character
 * @param hp      The health points
 * @param defense The resistance to damage
 * @param weight  The weight of the character
 * @param mana    The mana of the character
 */
abstract class AMagicalCharacter(
                                  name: String,
                                  hp: Int,
                                  defense: Int,
                                  weight: Int,
                                  mana: Int
                                ) extends ACharacter(name,hp,defense,weight)
                                    with IMagicalCharacter {
  private var _mana: Int = constrainMana(mana)
  Require.Stat(mana, "Mana") atLeast 0
  override def getMana: Int = _mana
  override def setMana(mana: Int): Unit = {
    _mana = constrainMana(mana)
  }
  /**
   *
   * @param mana The original mana value
   * @return The final valid mana value
   */
  private def constrainMana(mana: Int): Int = {
    mana match {
      case n if n < 1 => 1
      case _ => mana
    }
  }
}
