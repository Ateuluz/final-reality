package modelTest.entitiesTest.charactersTest.paladinTest

import exceptions.InvalidActionException
import model.armament.IWeapon
import model.armament.sword.Sword
import model.entities.characters.{IAxeBearer, IBowBearer, IStaffUser, ISwordBearer, IWandUser}
import model.entities.characters.paladin.Paladin

class PaladinTest extends munit.FunSuite{
  var ch1: Paladin = _
  var ch2: Paladin = _
  var wp1: Sword   = _

  override def beforeEach(context: BeforeEach): Unit = {
    wp1 = new Sword("Excal", 10, 20)
    ch1 = new Paladin("John", 80, 20, 5)
    ch2 = new Paladin("Doe", 200, 10, 25)
    ch2.equip(wp1)
  }

  test("Weapon User") {
    assertEquals(ch1.isInstanceOf[ISwordBearer],true,"Should be allowed")
    assertEquals(ch1.isInstanceOf[IAxeBearer],true,"Should be allowed")
    assertEquals(ch1.isInstanceOf[IBowBearer],false,"Shouldn't be allowed")
    assertEquals(ch1.isInstanceOf[IWandUser],false,"Shouldn't be allowed")
    assertEquals(ch1.isInstanceOf[IStaffUser],false,"Shouldn't be allowed")
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

  test("Cannot Assign Owned Weapon") {
    interceptMessage[InvalidActionException](
      "An invalid action was found -- Assigning Owned Weapon"
    ) {
      ch1.equip(wp1)
    }
  }
}
