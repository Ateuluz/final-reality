package controller.inputhandler

import scala.collection.mutable.ArrayBuffer

class ForcedInputHandler(
                          var inputSeq: ArrayBuffer[String]
                        ) extends IInputHandler {
  private var _current: Int = 0

  /** Ateuluz
   *
   * @param prompt String with instructions
   * @return Console input
   */
  override def getInput(prompt: String): String = {
    println(prompt)
    val output: String = inputSeq(_current)
    _current += 1
    println(output)
    output
  }
}
