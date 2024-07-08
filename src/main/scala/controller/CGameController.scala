package controller

import model.armament.sword.Sword
import model.entities.enemies.enemy.Enemy
import model.entities.playablecharacters.warrior.Warrior
import model.teams.enemies.Enemies
import model.teams.party.Party
import controller.inputhandler.{ConsoleInputHandler, IInputHandler}
import model.armament.IWeapon
import model.armament.axe.Axe
import model.armament.bow.Bow
import model.armament.staff.Staff
import model.armament.wand.Wand
import model.entities.playablecharacters.ICharacter
import model.entities.playablecharacters.blackmage.BlackMage
import model.entities.playablecharacters.ninja.Ninja
import model.entities.playablecharacters.paladin.Paladin
import model.entities.playablecharacters.whitemage.WhiteMage
import model.spells.basicmagic.concrete.Fireball
import model.spells.darkmagic.concrete.DevilsContract
import model.spells.lightmagic.heal.Heal

import scala.collection.mutable.ArrayBuffer

/** Ateuluz
 *
 * A concrete game controller, defining specific actions
 * This specific Game Controller sets a default initial state
 */
class CGameController(
                       inputHandler: IInputHandler,
                       isTesting: Boolean = false
                     ) extends AGameController(inputHandler, isTesting) {
//                     ) extends AGameController(new ConsoleInputHandler) {

  /** Ateuluz
   *
   *  @return The action bar raise constant
   */
  override def raiseConstant: Int = 20

  //region Set Defaults
  private val _inventory: ArrayBuffer[IWeapon] = ArrayBuffer[IWeapon](
    new Sword ("Excalibur", 27, 18),
    new Axe   ("Tomahawk" , 22, 14),
    new Bow   ("Olive"    , 10,  6),
    new Wand  ("Wanda"    ,  2,  4, 10),
    new Staff ("Cosmo"    ,  8, 12, 18)
  )

  private val _characters: ArrayBuffer[ICharacter] = ArrayBuffer[ICharacter](
    new Paladin   ("Mark"     , 220,22,110),
    new Warrior   ("Bob"      , 270,18,120),
    new Ninja     ("Garu"     , 150,10, 60),
    new WhiteMage ("Gandalf"  , 120,12, 80,100),
    new BlackMage ("Voldemort", 140,10, 90,100),
  )
  _characters(0).equip(new Sword("Rusty Sword", 10, 10))
  _characters(1).equip(new Sword("Rusty Sword", 10, 10))
  _characters(2).equip(new Sword("Rusty Sword", 10, 10))
  _characters(3).equip(new Wand("Old Wand", 10, 10, 10))
  _characters(4).equip(new Wand("Old Wand", 10, 10, 10))
  _characters(3).asMagical.addSpell(new Heal            (10, 20))
  _characters(3).asMagical.addSpell(new Fireball        (30, 40))
  _characters(4).asMagical.addSpell(new DevilsContract  (20, 10))
  _characters(4).asMagical.addSpell(new Fireball        (40, 55))

  /** Ateuluz
   *
   *  @return Stored weapons available
   */
  override def weaponInventory: ArrayBuffer[IWeapon] = _inventory

  /**
   *
   *  @return Stored characters available
   */
  override def characterInventory: ArrayBuffer[ICharacter] = _characters

  turnScheduler.party = new Party(
    new Warrior("Nameless 1",100,6,80),
    new Warrior("Nameless 2",105,5,85),
    new Warrior("Nameless 3",110,5,90)
  )
  turnScheduler.party.get.getMembers(0).equip(new Sword("Rusty Sword", 10, 10))
  turnScheduler.party.get.getMembers(1).equip(new Sword("Rusty Sword", 10, 10))
  turnScheduler.party.get.getMembers(2).equip(new Sword("Rusty Sword", 10, 10))

  turnScheduler.enemyTeam = new Enemies(
    new Enemy("Lower Demon 1", 50, 5, 15, 30),
    new Enemy("Lower Demon 2", 60, 4, 18, 40)
  )
  //endregion


}
