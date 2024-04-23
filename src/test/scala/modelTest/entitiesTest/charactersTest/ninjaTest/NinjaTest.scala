package modelTest.entitiesTest.charactersTest.ninjaTest

import model.armament.AWeapon
import model.armament.sword.Sword
import model.entities.characters.{IAxeBearer, IBowBearer, IStaffUser, ISwordBearer, IWandUser}
import model.entities.characters.ninja.Ninja

class NinjaTest extends munit.FunSuite{
  var tstSbjt1: Ninja   = _
  var tstSbjt2: Ninja   = _
  var tstObjt1: Sword   = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstObjt1 = new Sword("Excal", 10, 20, tstSbjt2)
    tstSbjt1 = new Ninja("John", 80, 20, 5)
    tstSbjt2 = new Ninja("Doe", 200, 10, 25, tstObjt1)
  }

  test("Weapon User") {
    assertEquals(tstSbjt1.isInstanceOf[ISwordBearer],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[IAxeBearer],false,"Shouldn't be allowed")
    assertEquals(tstSbjt1.isInstanceOf[IBowBearer],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[IWandUser],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[IStaffUser],false,"Shouldn't be allowed")
  }

  test("Weapon Ownership") {
    val expected: Sword = tstObjt1
    assertEquals(tstSbjt2.weapon,expected,"Weapon should be the given")
  }

  test("Null Weapon Ownership") {
    val expected: AWeapon = null
    assertEquals(tstSbjt1.weapon,expected,"Weapon should be null")
  }
}