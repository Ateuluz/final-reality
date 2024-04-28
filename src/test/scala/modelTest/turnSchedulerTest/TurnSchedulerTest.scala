package modelTest.turnSchedulerTest

import model.armament.bow.Bow
import model.armament.sword.Sword
import model.armament.wand.Wand
import model.entities.IEntity
import model.entities.characters.ninja.Ninja
import model.entities.characters.paladin.Paladin
import model.entities.characters.warrior.Warrior
import model.entities.characters.whiteMage.WhiteMage
import model.entities.enemies.enemy.Enemy
import model.turnScheduler.TurnScheduler

import scala.collection.mutable.ArrayBuffer

class TurnSchedulerTest extends munit.FunSuite {
  var TrSch: TurnScheduler = _
  var ch1:   Paladin       = _
  var ch2:   WhiteMage     = _
  var ch3:   Ninja         = _
  var ch4:   Warrior       = _
  var wp1:   Sword         = _
  var wp2:   Wand          = _
  var wp3:   Bow           = _
  var en1:   Enemy         = _
  var en2:   Enemy         = _
  override def beforeEach(context: BeforeEach): Unit = {
    TrSch = new TurnScheduler
    ch1   = new Paladin("A", 100, 100, 100)
    ch2   = new WhiteMage("B", 50, 50, 50, 100)
    ch3   = new Ninja("C", 20, 20, 20)
    ch4   = new Warrior("D", 200, 200, 200)
    wp1   = new Sword("S", 100, 100)
    wp2   = new Wand("W", 50, 50, 50)
    wp3   = new Bow("X", 20, 20)
    en1   = new Enemy("E1", 10, 10, 10, 10)
    en2   = new Enemy("E2", 5, 5, 5, 5)
    ch1.equip(wp1)
    ch2.equip(wp2)
    ch3.equip(wp3)
  }

  test("Has Characters") {
    val expected = ArrayBuffer[IEntity]()// define expected value
    val actual = TrSch.getCharacters// define actual value
    assertEquals(expected, actual, "Characters Not Defined - Explanation")
  }

  test("Has ActionBars") {
    val expected = ArrayBuffer[Int]()// define expected value
    val actual = TrSch.getActionBars// define actual value
    assertEquals(expected, actual, "Characters Not Defined - Explanation")
  }

  test("Add and Remove Characters") {
    TrSch.addCharacter(ch1)
    TrSch.addCharacter(ch2)
    TrSch.removeCharacter(ch1)
    val expected = ArrayBuffer[IEntity](ch2)// define expected value
    val actual = TrSch.getCharacters// define actual value
      assertEquals(expected, actual, "Character Not Added - Explanation")
  }

  test("Get Single Action Bar") {
    TrSch.addCharacter(ch1)
    val expected1 = 0// define expected value
    val actual1 = TrSch.getActionBar(ch1)// define actual value
    assertEquals(expected1, actual1, "Character Not Added - Explanation")

    val expected2 = -1// define expected value
    val actual2 = TrSch.getActionBar(ch2)// define actual value
    assertEquals(expected2, actual2, "Character Not Added - Explanation")
  }

  test("Calculate Max Action Bar") {
    val expected1 = 150// define expected value
    val actual1 = TrSch.getActionBarMax(ch1)// define actual value
      assertEquals(expected1, actual1, "Max Action Bar Calculation Incorrect - Explanation")
    val expected2 = 10// define expected value
    val actual2 = TrSch.getActionBarMax(en1)// define actual value
      assertEquals(expected2, actual2, "Max Action Bar Calculation Incorrect - Explanation")

    //val exception = intercept[IllegalArgumentException] {
    //  TrSch.getActionBarMax(wp1)
    //}
    //assert(exception.isInstanceOf[IllegalArgumentException])
    //assert(exception.getMessage == "Invalid class")
    /*Now type of argument is defined*/
  }

  test("Track Action Bars") {
    TrSch.addCharacter(ch1)
    val expected = ArrayBuffer[Int](0)// define expected value
    val actual = TrSch.getActionBars// define actual value
      assertEquals(expected, actual, "Action Bars Tracking Incorrect - Explanation")
  }

  test("Raise All Action Bars") {
    TrSch.addCharacter(ch1)
    TrSch.addCharacter(ch2)
    TrSch.raiseActionBars(10)
    val expected = ArrayBuffer[Int](10, 10)// define expected value
    val actual = TrSch.getActionBars// define actual value
      assertEquals(expected, actual, "Action Bars Not Raised Successfully - Explanation")
  }

  test("Reset Action Bar") {
    TrSch.addCharacter(ch1)
    TrSch.raiseActionBars(999)
    TrSch.reset(TrSch.getCharacters(0))
    TrSch.reset(ch2)
    val expected = 0// define expected value
    val actual = TrSch.getActionBars(0)// define actual value
      assertEquals(expected, actual, "Action Bar Reset Failed - Explanation")
  }

  test("Check Action Bar Full") {
    TrSch.addCharacter(ch1)
    TrSch.addCharacter(ch2)
    TrSch.raiseActionBars(TrSch.getActionBarMax(ch2))
    val expected1 = false// define expected value
    val actual1 = TrSch.isFull(TrSch.getCharacters(0))// define actual value
      assertEquals(expected1, actual1, "Action Bar Full - Explanation")
    val expected2 = true// define expected value
    val actual2 = TrSch.isFull(TrSch.getCharacters(1))// define actual value
      assertEquals(expected2, actual2, "Action Bar Not Full - Explanation")
    val expected3 = false// define expected value
    val actual3 = TrSch.isFull(ch3)// define actual value
      assertEquals(expected3, actual3, "Action Bar Exists - Explanation")
  }

  test("Return Characters with Full Bars, Ordered Descending by Surplus") {
    TrSch.addCharacter(ch1)
    TrSch.addCharacter(ch2)
    TrSch.addCharacter(ch3)
    TrSch.raiseActionBars(TrSch.getActionBarMax(ch1)-1)
    val expected = ArrayBuffer[IEntity](ch3, ch2)// define expected value
    val actual   = TrSch.getCharactersFull// define actual value
      assertEquals(expected, actual, "Incorrect Characters Returned - Explanation")
  }

  test("Designate Turn") {
    TrSch.addCharacter(ch1)
    TrSch.addCharacter(ch2)
    TrSch.raiseActionBars(TrSch.getActionBarMax(ch1))
    val expected: Any = ch2// define expected value
    val actual:   Any = TrSch.getAtTurn// define actual value
      assertEquals(expected, actual, "Turn Not Designated - Explanation")
  }

  test("Max Bar 0 for Empty Weapon") {
    TrSch.addCharacter(ch4)
    val expected: Int = 0// define expected value
    val actual:   Int = TrSch.getActionBarMax(ch4)// define actual value
      assertEquals(expected, actual, "Should Have 0 as Max Action Bar")
  }
}
