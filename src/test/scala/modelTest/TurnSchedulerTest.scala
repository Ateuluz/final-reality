package modelTest

import model.armament.{Bow, Sword, Wand}
import model.characters.{Ninja, Paladin, WhiteMage}
import model.enemies.Enemy

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
    TrSch: TurnScheduler = new TurnScheduler()
    ch1:   Paladin       = new Paladin("A", 100, 100, 100, wp1)
    ch2:   WhiteMage     = new WhiteMage("B", 50, 50, 50, 100, wp2)
    ch3:   Ninja         = new Ninja("C", 20, 20, 20, wp3)
    wp1:   Sword         = new Sword("S", 100, 100, ch1)
    wp2:   Wand          = new Wand("W", 50, 50, 50, ch2)
    wp3:   Bow           = new Bow("X", 20, 20, ch3)
    en1:   Enemy         = new Enemy("E1", 10, 10, 10, 10)
    en2:   Enemy         = new Enemy("E1", 5, 5, 5, 5)
  }

  test("Add Character") {
    val expected = ArrayBuffer(ch1)// define expected value
    val actual = TrSch.getCharacters()// define actual value
      assertEquals(expected, actual, "Character Not Added - Explanation")
  }

  test("Remove Character") {
    val expected = ArrayBuffer(ch2)// define expected value
    val actual = TrSch.getCharacters()// define actual value
      assertEquals(expected, actual, "Character Not Removed - Explanation")
  }

  test("Calculate Max Action Bar") {
    val expected = // define expected value
    val actual = TrSch.getMax()// define actual value
      assertEquals(expected, actual, "Max Action Bar Calculation Incorrect - Explanation")
  }

  test("Track Action Bars") {
    val expected = // define expected value
    val actual = TrSch.getAll()// define actual value
      assertEquals(expected, actual, "Action Bars Tracking Incorrect - Explanation")
  }

  test("Reset Action Bar") {
    val expected = // define expected value
    val actual = TrSch.reset(ch1)// define actual value
      assertEquals(expected, actual, "Action Bar Reset Failed - Explanation")
  }

  test("Raise All Action Bars") {
    val expected = // define expected value
    val actual = TrSch.raise()// define actual value
      assertEquals(expected, actual, "Action Bars Not Raised Successfully - Explanation")
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
