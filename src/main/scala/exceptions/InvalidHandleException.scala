package exceptions

/** Ateuluz
 * Custom exception to signal an invalid action within game has been made.
 *
 * This exception is designed to provide more specific feedback about
 * invalid actions made by the user. For instance, if one is unable
 * to add a member to a team, this exception could be thrown with a
 * detailed message indicating the nature of the problem.
 *
 * @example To throw the exception with specific details:
 * {{{
 * throw new InvalidHandleException("Cannot remove member, minimum amount reached.")
 * // => InvalidHandleException: An invalid user action was found -- Cannot remove member, minimum amount reached.
 * }}}
 *
 * @param details A descriptive message detailing the nature of the invalid action of the user.
 */
class InvalidHandleException(details: String) extends Exception(s"An invalid user action was found -- $details")