package controllertest

import controller.{AGameController, CGameController}
import controller.inputhandler.{ConsoleInputHandler, ForcedInputHandler, IInputHandler}
import controller.states.{AGameState, STransition}
import controller.states.start.StartPhase
import controller.states.turnphase.ApplyEffects
import exceptions.InvalidStateException
import model.armament.IWeapon
import model.armament.axe.Axe
import model.armament.bow.Bow
import model.armament.staff.Staff
import model.armament.sword.Sword
import model.armament.wand.Wand
import model.entities.enemies.enemy.Enemy
import model.entities.playablecharacters.ICharacter
import model.entities.playablecharacters.blackmage.BlackMage
import model.entities.playablecharacters.ninja.Ninja
import model.entities.playablecharacters.paladin.Paladin
import model.entities.playablecharacters.warrior.Warrior
import model.entities.playablecharacters.whitemage.WhiteMage
import model.spells.basicmagic.concrete.Fireball
import model.spells.darkmagic.concrete.DevilsContract
import model.spells.lightmagic.heal.Heal
import model.teams.enemies.Enemies
import model.teams.party.Party

import scala.collection.mutable.ArrayBuffer

class GameStateTest extends munit.FunSuite {
  var inh: ForcedInputHandler = _
  var con: CGameController    = _

  override def beforeEach(context: BeforeEach): Unit = {
    inh = new ForcedInputHandler(new ArrayBuffer[String]())
    con = new CGameController (inh, true)
  }

  test("Game Begin") {
    inh.inputSeq = ArrayBuffer("")
    con.testStep()

    assert(con.state.isInstanceOf[StartPhase])
  }

  test("Game Loop Begin") {
    inh.inputSeq = ArrayBuffer("", "0")
    con.testStep()
    con.testStep()

    assert(con.state.isInstanceOf[STransition])
  }

  test("Assign Weapon at Begin") {
    inh.inputSeq = ArrayBuffer("", "1", "1", "1")
    con.testStep()
    con.testStep()

    assert(con.state.isInstanceOf[StartPhase])
    assertEquals(
      con.turnScheduler.party.get.getMembers(0).getWeapon.get,
      con.weaponInventory(0)
    )
  }

  test("Replace Character at Begin") {
    inh.inputSeq = ArrayBuffer("", "1", "2", "1")
    con.testStep()
    con.testStep()

    assert(con.state.isInstanceOf[StartPhase])
    assertEquals(
      con.turnScheduler.party.get.getMembers(0),
      con.characterInventory(0),
      s"${con.characterInventory(0).getName} | ${con.turnScheduler.party.get.getMembers(0).getName}\n${con.turnScheduler.party.get.getMembers(0).getName}|${con.turnScheduler.party.get.getMembers(1).getName}|${con.turnScheduler.party.get.getMembers(2).getName}"
    )
  }

  test("Equip at Turn") {
    inh.inputSeq = ArrayBuffer("", "0", "0", "2")
    con.testStep()
    con.testStep()

    con.testStep()
    con.testStep()
    con.testStep()

    assert(con.state.isInstanceOf[ApplyEffects])

    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()

    assert(con.state.isInstanceOf[ApplyEffects])

    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
    con.testStep()
  }

  test("For Magical") {
    inh.inputSeq = ArrayBuffer("", "1", "2", "4", "0", "2", "1", "1", "1", "1", "1", "2")
    for (i <- 0 until 50) {
      con.testStep()
    }
  }

  test("Effects Applied"){
    inh.inputSeq = ArrayBuffer("", "1", "2", "4", "0", "2", "2", "1", "1", "1", "1", "2")
    for (i <- 0 until 50) {
      con.testStep()
    }
  }

  test("Effects Applied 2"){
    inh.inputSeq = ArrayBuffer("", "1", "2", "4", "2", "2", "5", "2", "1", "5", "0", "2", "2", "1", "2", "1", "2", "2",
      "1", "2",
      "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
    )
    for (i <- 0 until 80) {
      con.testStep()
    }
  }

  test("Can Create Real Game") {
    val controller1 = new CGameController(new ConsoleInputHandler, true)
    val controller2 = new CGameController(new ConsoleInputHandler, false)
    val controller3 = new CGameController(new ConsoleInputHandler)
    class TController(handler: IInputHandler) extends AGameController(handler) {
      override def reset(): Unit = {}
      override def raiseConstant: Int = 1
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
      _characters(4).asMagical.addSpell(new DevilsContract  (10, 30))
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
    val controller4 = new TController(new ForcedInputHandler(ArrayBuffer[String]()))
  }

  test("Game Flow Long") {
    inh.inputSeq = ArrayBuffer("",
      "1", "2", "4", "1", "1", "4", "0",
      "2", "2", "1",
      "1", "1",
      "0", "1",
      "0", "5",
      "0", "3", "1", "1", "2",
      "1", "2",
      "1", "2",
      "0", "4", "1", "2",
      "1", "2",
      "",
      /*Rematch*/
      "1", "2", "4", "1", "1", "4", "0",
      "2", "2", "1",
      "1", "1",
      "0", "1",
      "0", "5",
      "0", "3", "1", "1", "2",
      "1", "2",
      "1", "2",
      "0", "4", "1", "2",
      "1", "2",
      "0", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
    for (i <- 0 until 400)
      con.testStep()
    true
  }

  test("Invalid States") {
    class InvalidState extends AGameState(new CGameController(new ForcedInputHandler(ArrayBuffer[String]())))
    interceptMessage[InvalidStateException](
      "An invalid state call was found -- This State cannot handle the intended action"
    ) {
      val invalidState = new InvalidState
      invalidState.step()
    }
  }

  test("Switch Difficulty") {
    inh.inputSeq = ArrayBuffer("","4","1","4","2","4","3","0","1","1","1","1","1")
    for (i <- 0 until 9)
      con.testStep()
  }
}
