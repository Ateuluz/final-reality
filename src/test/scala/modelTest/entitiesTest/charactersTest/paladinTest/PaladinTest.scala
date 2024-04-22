package modelTest.entitiesTest.charactersTest.paladinTest

import model.armament.Weapon
import model.armament.sword.Sword
import model.entities.characters.{AxeBearer, BowBearer, StaffUser, SwordBearer, WandUser}
import model.entities.characters.paladin.Paladin

class PaladinTest extends munit.FunSuite{
  var tstSbjt1: Paladin = _
  var tstSbjt2: Paladin = _
  var tstSbjt3: Paladin = _
  var tstObjt1: Sword   = _

  override def beforeEach(context: BeforeEach): Unit = {
    tstObjt1 = new Sword("Excal", 10, 20, tstSbjt2)
    tstSbjt1 = new Paladin("John", 80, 20, 5)
    tstSbjt2 = new Paladin("Doe", 200, 10, 25, tstObjt1)
  }

  test("Weapon User") {
    assertEquals(tstSbjt1.isInstanceOf[SwordBearer],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[AxeBearer],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[BowBearer],false,"Shouldn't be allowed")
    assertEquals(tstSbjt1.isInstanceOf[WandUser],false,"Shouldn't be allowed")
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
