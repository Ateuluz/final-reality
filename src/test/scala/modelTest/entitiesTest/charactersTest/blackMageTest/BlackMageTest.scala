package modelTest.entitiesTest.charactersTest.blackMageTest

import model.armament.AWeapon
import model.armament.sword.Sword
import model.entities.characters.{IAxeBearer, IBowBearer, IStaffUser, ISwordBearer, IWandUser}
import model.entities.characters.blackMage.BlackMage

class BlackMageTest extends munit.FunSuite{
  var wp1: Sword     = _
  var ch1: BlackMage = _
  var ch2: BlackMage = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    wp1 = new Sword("C", 10, 20, ch2)
    ch1 = new BlackMage("A", 80, 20, 5, 100)
    ch2 = new BlackMage("B", 200, 10, 25, 200, wp1)
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
    val actual = ch2.weapon
    assertEquals(actual,expected,"Weapon should be the given")
  }

  test("Null Weapon Ownership") {
    val expected: AWeapon = null
    assertEquals(ch1.weapon,expected,"Weapon should be null")
  }
}

/*
When defining the weapon of a character,
first is creating the weapon
then creating the character
the next steps depend
either the character is created with the weapon or the weapon is assigned later on
at last you define the owner of the weapon

If the previous steps are not followed, the assigned weapon will be different than the created one
this is because you assign the promise of a weapon, which is initialized later
thus needing a new place in memory
this generates the mismatch between the assigned and the intended

Might delete the constructor that allows for assignment upon initialization
 */