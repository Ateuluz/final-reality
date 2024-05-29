package exceptions

/**
 * Custom exception to signal an invalid spell casting within game has been made.
 *
 * This exception is designed to provide more specific feedback about
 * invalid spell casts magical characters could make. For instance, if a
 * mage is unable to target an ally, this exception could be thrown with a
 * detailed message indicating the nature of the problem.
 *
 * @example To throw the exception with specific details:
 * {{{
 * throw new InvalidCasterException("Spell cannot be cast.")
 * // => InvalidHolderException: An invalid spell caster was found -- Spell cannot be cast.
 * }}}
 *
 * @param details A descriptive message detailing the nature of the invalid action.
 */
class InvalidCasterException(details: String) extends Exception(s"An invalid spell caster was found -- $details")