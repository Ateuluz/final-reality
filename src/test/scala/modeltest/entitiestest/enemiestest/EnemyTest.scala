package modeltest.entitiestest.enemiestest

import exceptions.{InvalidActionException, InvalidStatException}
import model.entities.playablecharacters.ninja.Ninja
import model.entities.enemies.enemy.Enemy

class EnemyTest extends munit.FunSuite {
  var en1: Enemy = _
  var en2: Enemy = _
  var ch1: Ninja = _
  var name: String   = _
  var threshold: Int = _
  var value: Int     = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    en1 = new Enemy("Mike", 100, 10, 20, 50)
    en2 = new Enemy("Ross", 120, 15, 15, 60)
    ch1 = new Ninja("X", 115, 15, 15)
  }

  test("Has Attack") {
    val expected = 20
    val actual = en1.getAttack
    assertEquals(actual,expected)
  }

  test("Limited Variable Ranges") {
    this.name = "Attack"
    this.threshold = 1
    this.value = -1
    interceptMessage[InvalidStatException](
      s"An invalid stat was found -- ${this.name} should be at least ${this.threshold} but was ${this.value}"
    ) {
      en2 = new Enemy("Joe", 1, 1, -1, 1)
    }
  }
  //test("Limited Variable Ranges") {
  //  var allowNegativeHP: Boolean = false
  //  var allowNegativeDefense: Boolean = false
  //  var allowNegativeAttack: Boolean = false
  //  var allowNegativeWeight: Boolean = false
  //  try {
  //    en1 = new Enemy("Joe", -1, 1, 1, 1)
  //    allowNegativeHP = true
  //  } catch {
  //    case _: java.lang.IllegalArgumentException =>
  //  }
  //  try {
  //    en2 = new Enemy("Joe", 1, -1, 1, 1)
  //    allowNegativeDefense = true
  //  } catch {
  //    case _: java.lang.IllegalArgumentException =>
  //  }
  //  try {
  //    en2 = new Enemy("Joe", 1, 1, -1, 1)
  //    allowNegativeAttack = true
  //  } catch {
  //    case _: java.lang.IllegalArgumentException =>
  //  }
  //  try {
  //    en1 = new Enemy("Joe", 1, 1, 1, -1)
  //    allowNegativeWeight = true
  //  } catch {
  //    case _: java.lang.IllegalArgumentException =>
  //  }
  //  assertEquals(allowNegativeHP,false,"Negative HP shouldn't be allowed")
  //  assertEquals(allowNegativeDefense,false,"Negative Defense shouldn't be allowed")
  //  assertEquals(allowNegativeAttack,false,"Negative or zero Attack shouldn't be allowed")
  //  assertEquals(allowNegativeWeight,false,"Negative or zero Weight shouldn't be allowed") // Speed related
  //}

  //test("Out of Range Attributes set to Arbitrary") {
  //  en1 = new Enemy("Joe", -1, 1, 1, 1)
  //  assertEquals(en1.getHp,0)
  //  en1 = new Enemy("Joe", 1, -1, 1, 1)
  //  assertEquals(en1.getDefense,0)
  //  en1 = new Enemy("Joe", 1, 1, -1, 1)
  //  assertEquals(en1.getAttack,1)
  //  en1 = new Enemy("Joe", 1, 1, 1, -1)
  //  assertEquals(en1.getWeight,1)
  //}

  test("Can't Attack Ally") {
    interceptMessage[InvalidActionException](
      s"An invalid action was found -- Enemy cannot attack this entity."
    ) {
      en1.attack(en2)
    }
  }

  test("Can Attack Character") {
    try {
      en1.attack(ch1)
    } catch {
      case _: InvalidActionException => fail("The Enemy Is Supposed Attack")
      case _ =>
        assertEquals(
          ch1.getHp,
          110,
          "Incorrect damage assignation"
        )
    }
  }
}
