package modelTest.armamentTest

import model.armament.staff.Staff
import model.armament.sword.Sword

class WeaponTest extends munit.FunSuite{
  var wp1: Sword = _
  var wp2: Staff = _

  override def beforeEach(context: BeforeEach): Unit = {
    wp1 = new Sword("A", 5, 10)
    wp2 = new Staff("B", 20, 30, 25)
  }

  //test("Limited Variable Ranges") {
  //  var allowNegativeHP: Boolean = false
  //  var allowNegativeWeight: Boolean = false
  //  var allowNegativeMagicAttack: Boolean = false
  //  try {
  //    wp1 = new Sword("A", -1, 1)
  //    allowNegativeHP = true
  //  } catch {
  //    case _: java.lang.IllegalArgumentException =>
  //  }
  //  try {
  //    wp1 = new Sword("A", 1, -1)
  //    allowNegativeWeight = true
  //  } catch {
  //    case _: java.lang.IllegalArgumentException =>
  //  }
  //  try {
  //    wp2 = new Staff("B", 1, 1, -1)
  //    allowNegativeMagicAttack = true
  //  } catch {
  //    case _: java.lang.IllegalArgumentException =>
  //  }
  //  assertEquals(allowNegativeHP,false,"Negative HP shouldn't be allowed")
  //  assertEquals(allowNegativeWeight,false,"Negative or zero Weight shouldn't be allowed") // Speed related
  //  assertEquals(allowNegativeMagicAttack,false,"Negative MagicAttack shouldn't be allowed")
  //}

  test("Out of Range Attributes set to Arbitrary") {
    wp1 = new Sword("Joe", -1, 1)
    assertEquals(wp1.getAttack,1)
    wp1 = new Sword("Joe", 1, -1)
    assertEquals(wp1.getWeight,0)
    wp2 = new Staff("Joe", 1, 1, -1)
    assertEquals(wp2.getMagicAttack,1)
  }

  test("Has Name") {
    assertEquals(wp1.getName,"A")
  }
}