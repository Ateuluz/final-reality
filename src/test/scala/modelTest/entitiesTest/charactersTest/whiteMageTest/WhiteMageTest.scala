package modelTest.entitiesTest.charactersTest.whiteMageTest

import model.armament.IWeapon
import model.armament.wand.Wand
import model.entities.characters.{IAxeBearer, IBowBearer, IStaffUser, ISwordBearer, IWandUser}
import model.entities.characters.whiteMage.WhiteMage

class WhiteMageTest extends munit.FunSuite{
  var ch1: WhiteMage = _
  var ch2: WhiteMage = _
  var wp1: Wand      = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    wp1 = new Wand("Excal", 10, 20, 10)
    ch1 = new WhiteMage("John", 80, 20, 5, 50)
    ch2 = new WhiteMage("Doe", 200, 10, 25, 150)
    ch2.setWeapon(wp1)
  }

  test("Weapon User") {
    assertEquals(ch1.isInstanceOf[ISwordBearer],false,"Shouldn't be allowed")
    assertEquals(ch1.isInstanceOf[IAxeBearer],false,"Shouldn't be allowed")
    assertEquals(ch1.isInstanceOf[IBowBearer],true,"Should be allowed")
    assertEquals(ch1.isInstanceOf[IWandUser],true,"Should be allowed")
    assertEquals(ch1.isInstanceOf[IStaffUser],true,"Should be allowed")
  }

  test("Weapon Ownership") {
    val expected: Wand = wp1
    val actual = ch2.getWeapon
    actual match {
      case Some(actual: IWeapon) => assertEquals(actual, expected)
      case _ => fail("Weapon should be the given")
    }
  }

  test("Null Weapon Ownership") {
    val expected = None
    assertEquals(ch1.getWeapon,expected,"Weapon should be null")
  }
}
