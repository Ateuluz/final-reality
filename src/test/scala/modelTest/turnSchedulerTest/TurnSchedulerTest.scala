package modelTest.turnSchedulerTest

import model.armament.{Bow, Sword, Wand}
import model.characters.{Ninja, Paladin, WhiteMage}
import model.enemies.Enemy
import model.turnScheduler.TurnScheduler

import scala.collection.mutable.ArrayBuffer

class TurnSchedulerTest extends munit.FunSuite {
  var TrSch: TurnScheduler = _
  var ch1:   Paladin       = _
  var ch2:   WhiteMage     = _
  var ch3:   Ninja         = _
  var wp1:   Sword         = _
  var wp2:   Wand          = _
  var wp3:   Bow           = _
  var en1:   Enemy         = _
  var en2:   Enemy         = _
  override def beforeEach(context: BeforeEach): Unit = {
    TrSch = new TurnScheduler
    ch1   = new Paladin("A", 100, 100, 100, wp1)
    ch2   = new WhiteMage("B", 50, 50, 50, 100, wp2)
    ch3   = new Ninja("C", 20, 20, 20, wp3)
    wp1   = new Sword("S", 100, 100, ch1)
    wp2   = new Wand("W", 50, 50, 50, ch2)
    wp3   = new Bow("X", 20, 20, ch3)
    en1   = new Enemy("E1", 10, 10, 10, 10)
    en2   = new Enemy("E1", 5, 5, 5, 5)
  }

  test("Has Characters") {
    val expected = ArrayBuffer[Any]()// define expected value
    val actual = TrSch.characters// define actual value
    assertEquals(expected, actual, "Characters Not Defined - Explanation")
  }

  test("Has ActionBars") {
    val expected = ArrayBuffer[Int]()// define expected value
    val actual = TrSch.actionBars// define actual value
    assertEquals(expected, actual, "Characters Not Defined - Explanation")
  }

  test("Add and Remove Characters") {
    TrSch.addCharacter(ch1)
    TrSch.addCharacter(ch2)
    TrSch.removeCharacter(ch1)
    val expected = ArrayBuffer[Any](ch2)// define expected value
    val actual = TrSch.getCharacters// define actual value
      assertEquals(expected, actual, "Character Not Added - Explanation")
  }

  test("Calculate Max Action Bar") {
    val expected = 150// define expected value
    val actual = TrSch.getMax(ch1)// define actual value
      assertEquals(expected, actual, "Max Action Bar Calculation Incorrect - Explanation")
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
    TrSch.raiseActionBars
    val expected = ArrayBuffer[Int](10, 10)// define expected value
    val actual = TrSch.getActionBars// define actual value
      assertEquals(expected, actual, "Action Bars Not Raised Successfully - Explanation")
  }

  test("Reset Action Bar") {
    TrSch.addCharacter(ch1)
    TrSch.raiseActionBars
    TrSch.reset(TrSch.getCharacters(0))
    val expected = 0// define expected value
    val actual = TrSch.getActionBars(0)// define actual value
      assertEquals(expected, actual, "Action Bar Reset Failed - Explanation")
  }

  test("Check Action Bar Full") {
    val expected = true// define expected value
    val actual = TrSch.isFull(ch1)// define actual value
      assertEquals(expected, actual, "Action Bar Not Full - Explanation")
  }

  test("Return Characters with Full Bars, Ordered Descending by Surplus") {
    val expected = ArrayBuffer(ch1, ch3)// define expected value
    val actual = TrSch.getFull()// define actual value
      assertEquals(expected, actual, "Incorrect Characters Returned - Explanation")
  }

  test("Designate Turn") {
    val expected = ch1// define expected value
    val actual = TrSch.getTurn()// define actual value
      assertEquals(expected, actual, "Turn Not Designated - Explanation")
  }

}
