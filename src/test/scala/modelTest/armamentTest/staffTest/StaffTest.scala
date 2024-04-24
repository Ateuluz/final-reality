package modelTest.armamentTest.staffTest

import model.armament.staff.Staff
import model.entities.characters.{ICharacter, IStaffUser}
import model.entities.characters.blackMage.BlackMage
import model.entities.characters.warrior.Warrior

class StaffTest extends munit.FunSuite() {
  var ch1: IStaffUser = _
  var ch2: Warrior = _
  var wp1: Staff = _
  var wp2: Staff = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    ch1 = new BlackMage("A", 10, 10, 10, 10)
    wp1 = new Staff("B", 5, 10, 5)
    ch1.requestBindWeapon(wp1)
    wp2 = new Staff("C", 20, 30, 10)
  }

  test("Owner Check") {
    val expected1 = ch1
    val actual1 = wp1.getOwner
    actual1 match {
      case Some(actual: ICharacter) => assertEquals(actual, expected1)
      case _ => fail("The returned instance is not an IAxeBearer")
    }
    val expected2 = true
    val actual2 = wp2.getOwner.isEmpty
    assertEquals(actual2,expected2,"The returned instance is not empty")
  }

  test("Non User cannot Equip") {
    ch2 = new Warrior("X", 2, 2, 2)
    ch2.requestBindWeapon(wp2)
    assertEquals(wp2.getOwner,None)
  }
}
