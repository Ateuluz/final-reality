package modelTest.entitiesTest.charactersTest.whiteMageTest

import model.armament.AWeapon
import model.armament.wand.Wand
import model.entities.characters.{IAxeBearer, IBowBearer, IStaffUser, ISwordBearer, IWandUser}
import model.entities.characters.whiteMage.WhiteMage

class WhiteMageTest extends munit.FunSuite{
  var tstSbjt1: WhiteMage = _
  var tstSbjt2: WhiteMage = _
  var tstObjt1: Wand      = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstObjt1 = new Wand("Excal", 10, 20, 10, tstSbjt2)
    tstSbjt1 = new WhiteMage("John", 80, 20, 5, 50)
    tstSbjt2 = new WhiteMage("Doe", 200, 10, 25, 150, tstObjt1)
  }

  test("Weapon User") {
    assertEquals(tstSbjt1.isInstanceOf[ISwordBearer],false,"Shouldn't be allowed")
    assertEquals(tstSbjt1.isInstanceOf[IAxeBearer],false,"Shouldn't be allowed")
    assertEquals(tstSbjt1.isInstanceOf[IBowBearer],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[IWandUser],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[IStaffUser],true,"Should be allowed")
  }

  test("Weapon Ownership") {
    val expected: Wand = tstObjt1
    assertEquals(tstSbjt2.weapon,expected,"Weapon should be the given")
  }

  test("Null Weapon Ownership") {
    val expected: AWeapon = null
    assertEquals(tstSbjt1.weapon,expected,"Weapon should be null")
  }
}
