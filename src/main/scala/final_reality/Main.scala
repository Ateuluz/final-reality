package final_reality

import controller.CGameController
import controller.inputhandler.ConsoleInputHandler

object Main {
  def main(args: Array[String]): Unit = {
    val inputHandler = new ConsoleInputHandler
    val controller   = new CGameController(inputHandler)

    controller.step()
  }

}
