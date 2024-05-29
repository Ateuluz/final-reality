package exceptions

/**
 * Custom exception to signal an invalid action within game has been made.
 *
 * This exception is designed to provide more specific feedback about
 * invalid actions entities could make. For instance, if a character is unable
 * to attack an ally, this exception could be thrown with a detailed
 * message indicating the nature of the problem.
 *
 * @example To throw the exception with specific details:
 * {{{
 * throw new InvalidActionException("Character cannot attack this entity.")
 * // => InvalidActionException: An invalid action was found -- Character cannot attack this entity.
 * }}}
 *
 * @param details A descriptive message detailing the nature of the invalid action.
 */
class InvalidActionException(details: String) extends Exception(s"An invalid action was found -- $details")