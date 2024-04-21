package modelTest.armamentTest.axeTest

import model.armament.axe.Axe
import model.characters.AxeBearer
import model.characters.paladin.Paladin

class AxeTest extends munit.FunSuite() {
  var ch1: AxeBearer = _
  var wp1: Axe = _
  var wp2: Axe = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    ch1 = new Paladin("A", 10, 10, 10, wp1)
    wp1 = new Axe("B", 5, 10, ch1)
    wp2 = new Axe("C", 20, 30)
  }

  test("Owner Check") {
    val expected1 = ch1
    val actual1   = wp1.owner
      assertEquals(actual1,expected1)
    val expected2: AxeBearer = null
    val actual2              = wp2.owner
      assertEquals(actual2,expected2)
  }
}
