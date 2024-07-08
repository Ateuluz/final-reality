package controller

/** Ateuluz
 *
 * An interface to handle states and their methods
 */
trait IGameState {

  /** Ateuluz
   *
   * Step method to handle timings
   */
  def step(): Unit

}
