package modelTest.charactersTest.ninjaTest

import model.armament.Weapon
import model.armament.sword.Sword
import model.characters.{AxeBearer, BowBearer, StaffUser, SwordBearer, WandUser}
import model.characters.ninja.Ninja

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
    assertEquals(tstSbjt1.isInstanceOf[SwordBearer],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[AxeBearer],false,"Shouldn't be allowed")
    assertEquals(tstSbjt1.isInstanceOf[BowBearer],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[WandUser],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[StaffUser],false,"Shouldn't be allowed")
  }

  test("Weapon Ownership") {
    val expected: Sword = tstObjt1
    assertEquals(tstSbjt2.weapon,expected,"Weapon should be the given")
  }

  test("Null Weapon Ownership") {
    val expected: Weapon = null
    assertEquals(tstSbjt1.weapon,expected,"Weapon should be null")
  }
}