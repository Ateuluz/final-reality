package modelTest.armamentTest.swordTest

import model.armament.sword.Sword
import model.characters.SwordBearer
import model.characters.warrior.Warrior

class SwordTest extends munit.FunSuite() {
  var ch1: SwordBearer = _
  var wp1: Sword = _
  var wp2: Sword = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    ch1 = new Warrior("A", 10, 10, 10, wp1)
    wp1 = new Sword("B", 5, 10, ch1)
    wp2 = new Sword("C", 20, 30)
  }

  test("Owner Check") {
    val expected1 = ch1
    val actual1   = wp1.owner
    assertEquals(actual1,expected1)
    val expected2: SwordBearer = null
    val actual2                = wp2.owner
    assertEquals(actual2,expected2)
  }
}
