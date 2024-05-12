package model.entities.characters

/**
 * When creating a character with magic capabilities
 * this interface will be 'extend ACharacter with' required.
 * It does not extend ICharacter since that is a
 * given trait for all characters in the first place.
 *
 * To be changed if needed later on (unlikely).
 */
trait IMagicalCharacter {
  /**
   *
   * @return The mana the magical character has left
   */
  def getMana: Int

  /**
   * Public while mana mechanics not disclosed.
   *
   * @param mana The mana we want the character to have
   */
  def setMana(mana: Int): Unit
}
