package exceptions

/**
 * Custom exception to signal an invalid weapon assignation encountered.
 *
 * This exception is designed to provide more specific feedback about
 * invalid weapon assignations. For instance, if a character is unable to equip
 * a weapon, this exception could be thrown with a detailed
 * message indicating the nature of the problem.
 *
 * @example To throw the exception with specific details:
 * {{{
 * throw new InvalidHolderException("Paladin character cannot equip this type of weapon.")
 * // => InvalidHolderException: An invalid holder was found -- Paladin character cannot equip this type of weapon.
 * }}}
 *
 * @param details A descriptive message detailing the nature of the invalid holder.
 */
class InvalidHolderException(details: String) extends Exception(s"An invalid holder was found -- $details")