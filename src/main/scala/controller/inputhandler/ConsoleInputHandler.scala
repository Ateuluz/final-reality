package controller.inputhandler

/** Ateuluz
 *
 * Concrete Input Handler, focused on console
 */
class ConsoleInputHandler extends IInputHandler {
  /** Ateuluz
   *
   * @param prompt String with instructions
   * @return Console input
   */
  override def getInput(prompt: String): String = {
    println(prompt)
    scala.io.StdIn.readLine()
  }
}
