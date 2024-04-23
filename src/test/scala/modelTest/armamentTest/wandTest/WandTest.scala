package modelTest.armamentTest.wandTest

import model.armament.wand.Wand
import model.entities.characters.IWandUser
import model.entities.characters.whiteMage.WhiteMage

class WandTest extends munit.FunSuite() {
  var ch1: IWandUser = _
  var wp1: Wand = _
  var wp2: Wand = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    ch1 = new WhiteMage("A", 10, 10, 10, 10, wp1)
    wp1 = new Wand("B", 5, 10, 5, ch1)
    wp2 = new Wand("C", 20, 30, 10)
  }

  test("Owner Check") {
    val expected1 = ch1
    val actual1   = wp1.owner
    assertEquals(actual1,expected1)
    val expected2: IWandUser = null
    val actual2             = wp2.owner
    assertEquals(actual2,expected2)
  }
}
