package modelTest.armamentTest.bowTest

import model.armament.bow.Bow
import model.entities.characters.IBowBearer
import model.entities.characters.ninja.Ninja

class BowTest extends munit.FunSuite() {
  var ch1: IBowBearer = _
  var wp1: Bow = _
  var wp2: Bow = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    ch1 = new Ninja("A", 10, 10, 10, wp1)
    wp1 = new Bow("B", 5, 10, ch1)
    wp2 = new Bow("C", 20, 30)
  }

  test("Owner Check") {
    val expected1 = ch1
    val actual1   = wp1.owner
      assertEquals(actual1,expected1)
    val expected2: IBowBearer = null
    val actual2              = wp2.owner
      assertEquals(actual2,expected2)
  }
}
