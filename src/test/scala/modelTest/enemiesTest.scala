package modelTest

import model.enemies.Enemy

class EnemyTest extends munit.FunSuite {
  var tstSbjt1: Enemy = _
  var tstSbjt2: Enemy = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstSbjt1 = new Enemy("Mike", 100, 10, 20, 50)
    tstSbjt2 = new Enemy("Ross", 120, 15, 15, 60)
  }

  test("Limited Variable Ranges") {
    var allowNegativeHP: Boolean = false
    var allowNegativeDefense: Boolean = false
    var allowNegativeAttack: Boolean = false
    var allowNegativeWeight: Boolean = false
    try {
      tstSbjt1 = new Enemy("Joe", -1, 1, 1, 1)
      allowNegativeHP = true
    } catch {
      case _: java.lang.IllegalArgumentException =>
    }
    try {
      tstSbjt2 = new Enemy("Joe", 1, -1, 1, 1)
      allowNegativeDefense = true
    } catch {
      case _: java.lang.IllegalArgumentException =>
    }
    try {
      tstSbjt2 = new Enemy("Joe", 1, -1, 1, 1)
      allowNegativeAttack = true
    } catch {
      case _: java.lang.IllegalArgumentException =>
    }
    try {
      tstSbjt1 = new Enemy("Joe", 1, 1, 1, -1)
      allowNegativeWeight = true
    } catch {
      case _: java.lang.IllegalArgumentException =>
    }
    assertEquals(allowNegativeHP,false,"Negative HP shouldn't be allowed")
    assertEquals(allowNegativeDefense,false,"Negative Defense shouldn't be allowed")
    assertEquals(allowNegativeAttack,false,"Negative or zero Attack shouldn't be allowed")
    assertEquals(allowNegativeWeight,false,"Negative or zero Weight shouldn't be allowed") // Speed related
  }
}
