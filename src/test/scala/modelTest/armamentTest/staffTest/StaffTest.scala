package modelTest.armamentTest.staffTest

import model.armament.staff.Staff
import model.characters.StaffUser
import model.characters.blackMage.BlackMage

class StaffTest extends munit.FunSuite() {
  var ch1: StaffUser = _
  var wp1: Staff = _
  var wp2: Staff = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    ch1 = new BlackMage("A", 10, 10, 10, 10, wp1)
    wp1 = new Staff("B", 5, 10, 5, ch1)
    wp2 = new Staff("C", 20, 30, 10)
  }

  test("Owner Check") {
    val expected1 = ch1
    val actual1   = wp1.owner
      assertEquals(actual1,expected1)
    val expected2: StaffUser = null
    val actual2              = wp2.owner
      assertEquals(actual2,expected2)
  }
}
