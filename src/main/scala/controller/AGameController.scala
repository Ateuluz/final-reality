package controller

import model.armament.IWeapon
import model.entities.IEntity
import model.entities.enemies.IEnemy
import model.entities.playablecharacters.{ICharacter, IMagicalCharacter}
import model.spells.ISpell
import model.turnscheduler.{ITurnScheduler, TurnScheduler}

/** Ateuluz
 *
 * An abstract class for implementing a game controller
 * All States will call upon a similar method on their State
 */
abstract class AGameController extends IGameController {

  private var _state: IGameState = new SStart(this)

  private val _turnScheduler: ITurnScheduler = new TurnScheduler

  /** Ateuluz
   *
   *  @return Current game state
   */
  override def state: IGameState = _state

  /** Ateuluz
   *
   * @param newState The state we intend to change the controller to
   */
  override def state_=(newState: IGameState): Unit = _state = newState

  /** Ateuluz
   *
    *  @return The turn scheduler
   */
  override def turnScheduler: ITurnScheduler = _turnScheduler

  //<editor-fold desc="Required Methods">
  /** Ateuluz
   *
   * Begin the game
   */
  override def gameBegin(): Unit = {
    _state.gameBegin()
  }

  /** Ateuluz
   *
   * @param unit The attacker
   * @param other The defender
   */
  override def unitAttackOther(unit: IEntity, other: IEntity): Unit = {
    _state.unitAttackOther(unit, other)
  }

  /** Ateuluz
   *
   * @param mage Mage attacking
   * @param target Enemy defending
   * @param spell Spell cast
   */
  override def mageCast(mage: IMagicalCharacter, target: IEnemy, spell: ISpell): Unit = {
    _state.mageCast(mage, target, spell)
  }

  /** Ateuluz
   *
   * @param char Character equipping
   * @param weapon Weapon equipped
   */
  override def characterEquip(char: ICharacter, weapon: IWeapon): Unit = {
    _state.characterEquip(char, weapon)
  }
  //</editor-fold>

  //<editor-fold desc="Controlling States">
  /** Ateuluz
   *
   * A method that that loops over transition state
   */
  override def loop(): Unit = _state.loop()

  /** Ateuluz
   *
   * A method that will begin the turn taking process
   */
  override def takeTurns(): Unit = _state.takeTurns()

  /** Ateuluz
   *
   * A method to end the game
   */
  override def end(): Unit = _state.end()
  //</editor-fold>

}
