package modelTest.entitiesTest.enemiesTest

import exceptions.InvalidStatException
import model.entities.enemies.enemy.Enemy

class EnemyTest extends munit.FunSuite {
  var en1: Enemy = _
  var en2: Enemy = _
  var name: String   = _
  var threshold: Int = _
  var value: Int     = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    en1 = new Enemy("Mike", 100, 10, 20, 50)
    en2 = new Enemy("Ross", 120, 15, 15, 60)
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
}
