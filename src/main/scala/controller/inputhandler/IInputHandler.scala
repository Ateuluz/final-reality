package controller.inputhandler

/** Ateuluz
 *
 * A way to handle input
 * Interface
 */
trait IInputHandler {

  /** Ateuluz
   *
   * @param prompt String with instructions
   * @return Console input
   */
  def getInput(prompt: String): String
}
