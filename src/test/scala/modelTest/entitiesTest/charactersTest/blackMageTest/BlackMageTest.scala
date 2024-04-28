package modelTest.entitiesTest.charactersTest.blackMageTest

import model.armament.IWeapon
import model.armament.sword.Sword
import model.entities.characters.{IAxeBearer, IBowBearer, IStaffUser, ISwordBearer, IWandUser}
import model.entities.characters.blackMage.BlackMage

class BlackMageTest extends munit.FunSuite{
  var wp1: Sword     = _
  var ch1: BlackMage = _
  var ch2: BlackMage = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    wp1 = new Sword("C", 10, 20)
    ch1 = new BlackMage("A", 80, 20, 5, 100)
    ch2 = new BlackMage("B", 200, 10, 25, 200)
    ch2.equip(wp1)
  }

  test("Weapon User") {
    assertEquals(ch1.isInstanceOf[ISwordBearer],true,"Should be allowed")
    assertEquals(ch2.isInstanceOf[IAxeBearer],false,"Shouldn't be allowed")
    assertEquals(ch1.isInstanceOf[IBowBearer],false,"Shouldn't be allowed")
    assertEquals(ch2.isInstanceOf[IWandUser],true,"Should be allowed")
    assertEquals(ch1.isInstanceOf[IStaffUser],true,"Should be allowed")
  }

  test("Weapon Ownership") {
    val expected: Sword = wp1
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