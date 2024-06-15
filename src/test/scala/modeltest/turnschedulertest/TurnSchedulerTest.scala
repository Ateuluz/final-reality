package modeltest.turnschedulertest

import exceptions.{InvalidActionException, InvalidHandleException}
import model.armament.bow.Bow
import model.armament.sword.Sword
import model.armament.wand.Wand
import model.entities.IEntity
import model.entities.playablecharacters.ninja.Ninja
import model.entities.playablecharacters.paladin.Paladin
import model.entities.playablecharacters.warrior.Warrior
import model.entities.playablecharacters.whitemage.WhiteMage
import model.entities.enemies.enemy.Enemy
import model.teams.enemies.Enemies
import model.teams.party.Party
import model.turnscheduler.TurnScheduler

import scala.collection.mutable.ArrayBuffer

class TurnSchedulerTest extends munit.FunSuite {
  var TrSch: TurnScheduler = _
  var ch1: Paladin = _
  var ch2: WhiteMage = _
  var ch3: Ninja = _
  var ch4: Warrior = _
  var wp1: Sword = _
  var wp2: Wand = _
  var wp3: Bow = _
  var en1: Enemy = _
  var en2: Enemy = _
  var en3: Enemy = _

  override def beforeEach(context: BeforeEach): Unit = {
    TrSch = new TurnScheduler
    ch1 = new Paladin("A", 100, 100, 100)
    ch2 = new WhiteMage("B", 50, 50, 50, 100)
    ch3 = new Ninja("C", 20, 20, 20)
    ch4 = new Warrior("D", 200, 200, 200)
    wp1 = new Sword("S", 100, 100)
    wp2 = new Wand("W", 50, 50, 50)
    wp3 = new Bow("X", 20, 20)
    en1 = new Enemy("E1", 10, 10, 10, 10)
    en2 = new Enemy("E2", 5, 5, 5, 5)
    en3 = new Enemy("E2", 5, 5, 1000, 5)
    ch1.equip(wp1)
    ch2.equip(wp2)
    ch3.equip(wp3)
  }

  test("Has Characters") {
    val expected = ArrayBuffer[IEntity]() // define expected value
    val actual = TrSch.entities // define actual value
    assertEquals(expected, actual, "Characters Not Defined - Explanation")
  }

  test("Has ActionBars") {
    val expected = ArrayBuffer[Int]() // define expected value
    val actual = TrSch.actionBars // define actual value
    assertEquals(expected, actual, "Characters Not Defined - Explanation")
  }

  test("Add and Remove Characters") {
    val Party1: Party = new Party(ch1,ch2,ch3)
    TrSch.party = Party1
    TrSch.remove(ch1)
    TrSch.remove(ch3)
    val expected = ArrayBuffer[IEntity](ch2) // define expected value
    val actual = TrSch.entities // define actual value
    assertEquals(expected, actual, "Character Not Added - Explanation")
  }

  test("Cannot Remove Characters Not Listed") {
    intercept[IndexOutOfBoundsException] {
      TrSch.remove(ch1)
    }
  }

  test("Get Single Action Bar") {
    val Party1: Party = new Party(ch1,ch2,ch3)
    TrSch.party = Party1
    val expected1 = 0 // define expected value
    val actual1 = TrSch.actionBarOf(ch1) // define actual value
    assertEquals(expected1, actual1, "Character Not Added - Explanation")

    TrSch.remove(ch2)
    val expected2 = -1 // define expected value
    val actual2 = TrSch.actionBarOf(ch2) // define actual value
    assertEquals(expected2, actual2, "Character Not Added - Explanation")
  }

  test("Calculate Max Action Bar") {
    val expected1 = 150 // define expected value
    val actual1 = TrSch.actionBarMaxOf(ch1) // define actual value
    assertEquals(expected1, actual1, "Max Action Bar Calculation Incorrect - Explanation")
    val expected2 = 10 // define expected value
    val actual2 = TrSch.actionBarMaxOf(en1) // define actual value
    assertEquals(expected2, actual2, "Max Action Bar Calculation Incorrect - Explanation")

    //val exception = intercept[IllegalArgumentException] {
    //  TrSch.getActionBarMax(wp1)
    //}
    //assert(exception.isInstanceOf[IllegalArgumentException])
    //assert(exception.getMessage == "Invalid class")
    /*Now type of argument is defined*/
  }

  test("Track Action Bars") {
    val Party1: Party = new Party(ch1,ch2,ch3)
    TrSch.party = Party1
    val expected = ArrayBuffer[Int](0,0,0) // define expected value
    val actual = TrSch.actionBars // define actual value
    assertEquals(expected, actual, "Action Bars Tracking Incorrect - Explanation")
  }

  test("Raise All Action Bars") {
    val Party1: Party = new Party(ch1,ch2,ch3)
    TrSch.party = Party1
    TrSch.remove(ch3)
    TrSch.raiseActionBars(10)
    val expected = ArrayBuffer[Int](10, 10) // define expected value
    val actual = TrSch.actionBars // define actual value
    assertEquals(expected, actual, "Action Bars Not Raised Successfully - Explanation")
  }

  test("Reset Action Bar") {
    val Party1: Party = new Party(ch1,ch2,ch3)
    TrSch.party = Party1
    TrSch.raiseActionBars(999)
    TrSch.reset(TrSch.entities(0))
    val expected = 0 // define expected value
    val actual = TrSch.actionBars(0) // define actual value
    assertEquals(expected, actual, "Action Bar Reset Failed - Explanation")

    TrSch.remove(ch2)
    TrSch.reset(ch2)
    // TODO: exception perhaps, so far nothing
  }

  //test("Check Action Bar Full") {
  //  TrSch.addCharacter(ch1)
  //  TrSch.addCharacter(ch2)
  //  TrSch.raiseActionBars(TrSch.getActionBarMax(ch2))
  //  val expected1 = false// define expected value
  //  val actual1 = TrSch.isFull(TrSch.getCharacters(0))// define actual value
  //    assertEquals(expected1, actual1, "Action Bar Full - Explanation")
  //  val expected2 = true// define expected value
  //  val actual2 = TrSch.isFull(TrSch.getCharacters(1))// define actual value
  //    assertEquals(expected2, actual2, "Action Bar Not Full - Explanation")
  //  val expected3 = false// define expected value
  //  val actual3 = TrSch.isFull(ch3)// define actual value
  //    assertEquals(expected3, actual3, "Action Bar Exists - Explanation")
  //}

  test("Return Characters with Full Bars, Ordered Descending by Surplus") {
    val Party1: Party = new Party(ch1,ch2,ch3)
    TrSch.party = Party1
    TrSch.raiseActionBars(TrSch.actionBarMaxOf(ch1) - 1)
    val expected = ArrayBuffer[IEntity](ch3, ch2) // define expected value
    val actual = TrSch.charactersFull // define actual value
    assertEquals(expected, actual, "Incorrect Characters Returned - Explanation")
  }

  test("Designate Turn") {
    val Party1: Party = new Party(ch1,ch2,ch3)
    TrSch.party = Party1
    TrSch.remove(ch3)
    TrSch.raiseActionBars(TrSch.actionBarMaxOf(ch1))
    val expected: Any = ch2 // define expected value
    val actual: Any = TrSch.atTurn // define actual value
    assertEquals(expected, actual, "Turn Not Designated - Explanation")
  }

  test("Empty Weapon Exception") {
    interceptMessage[InvalidActionException](
      "An invalid action was found -- Character has no weapon, thus weight cannot be assigned"
    ) {
//      val Party1: Party = new Party(ch1,ch2,ch4)
//      TrSch.party = Party1
//      TrSch.remove(ch1)
//      TrSch.remove(ch2)
      TrSch.actionBarMaxOf(ch4)
    }
  }

  test("Bind Party") {
    val Party1: Party = new Party(ch1,ch2,ch3)
    TrSch.party = Party1
    val expected = ArrayBuffer[IEntity](ch1, ch2, ch3) // define expected value
    val actual = TrSch.entities // define actual value
    assertEquals(expected, actual, "Incorrect Characters Returned - Explanation")
  }

  test("Teams Assign") {
    val Party1: Party = new Party(ch1,ch2,ch3)
    TrSch.party = Party1
    val Enemies1: Enemies = new Enemies(en1)
    TrSch.enemyTeam = Enemies1
    assertEquals(TrSch.party.get, Party1)
    assertEquals(TrSch.enemyTeam.get, Enemies1)

    interceptMessage[InvalidHandleException](
      "An invalid user action was found -- A Party is set already"
    ) {
      TrSch.party = Party1
    }

    interceptMessage[InvalidHandleException](
      "An invalid user action was found -- An Enemy Team is set already"
    ) {
      TrSch.enemyTeam = Enemies1
    }
  }

  test("Teams Unbind") {
    val Party1: Party = new Party(ch1,ch2,ch3)
    val Enemies1: Enemies = new Enemies(en1)

    interceptMessage[InvalidHandleException](
      "An invalid user action was found -- There is no linked Party"
    ) {
      TrSch.unbindParty()
    }
    interceptMessage[InvalidHandleException](
      "An invalid user action was found -- There is no linked Enemy Team"
    ) {
      TrSch.unbindEnemies()
    }

    TrSch.party = Party1
    TrSch.enemyTeam = Enemies1

    ch1.attack(en1)
    en3.attack(ch1)

    TrSch.removeDead()

    TrSch.unbindParty()
    TrSch.unbindEnemies()

    assert(TrSch.party.isEmpty)
    assert(TrSch.enemyTeam.isEmpty)
  }

  test("End Game Conditions") {

    assert(!TrSch.endgame)

    val Party1: Party = new Party(ch1,ch2,ch3)
    TrSch.party = Party1
    assert(!TrSch.endgame)

    TrSch.unbindParty()
    val Enemies1: Enemies = new Enemies(en1)
    TrSch.enemyTeam = Enemies1
    assert(!TrSch.endgame)

    TrSch.party = Party1
    assert(!TrSch.endgame)

    ch1.attack(en1)
    assert(TrSch.endgame)

    // Both dead will never happen in a real situation, but whatever
    en3.attack(ch1)
    en3.attack(ch2)
    en3.attack(ch3)
    assert(TrSch.endgame)
  }

  test("Other Endgame Branches") {
    val Party1: Party = new Party(ch1,ch2,ch3)
    val Enemies1: Enemies = new Enemies(en1)
    TrSch.party = Party1
    TrSch.enemyTeam = Enemies1

    en3.attack(ch1)
    en3.attack(ch2)
    en3.attack(ch3)
    assert(TrSch.endgame)
  }
}
