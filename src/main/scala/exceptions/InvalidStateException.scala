package exceptions

/** Ateuluz
 *
 * Custom exception to signal an invalid call for certain state has been made.
 *
 * This exception is designed to provide more specific feedback about
 * invalid state changes that could be made. For instance, calling endgame
 * from start state, this exception could be thrown with a detailed
 * message indicating the nature of the problem.
 *
 * @example To throw the exception with specific details:
 * {{{
 * throw new InvalidStateException("This State cannot handle the intended action")
 * // => InvalidStateException: An invalid state call was found -- This State cannot handle the intended action
 * }}}
 *
 * @param details A descriptive message detailing the nature of the invalid action.
 */
class InvalidStateException(details: String) extends Exception(s"An invalid state call was found -- $details")