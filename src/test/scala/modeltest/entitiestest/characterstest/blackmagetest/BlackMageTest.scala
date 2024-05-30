package modeltest.entitiestest.characterstest.blackmagetest

import exceptions.InvalidActionException
import model.armament.IWeapon
import model.armament.staff.Staff
import model.armament.sword.Sword
import model.entities.playablecharacters.{IAxeBearer, IBowBearer, IStaffUser, ISwordBearer, IWandUser}
import model.entities.playablecharacters.blackmage.BlackMage
import model.entities.enemies.enemy.Enemy
import model.spells.darkmagic.exspell2.ExSpell2

class BlackMageTest extends munit.FunSuite{
  var wp1: Sword     = _
  var wp2: Staff     = _
  var ch1: BlackMage = _
  var ch2: BlackMage = _
  var en1: Enemy     = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    wp1 = new Sword("C", 10, 20)
    wp2 = new Staff("D", 10, 10, 10)
    ch1 = new BlackMage("A", 80, 20, 5, 100)
    ch2 = new BlackMage("B", 200, 10, 25, 200)
    en1 = new Enemy("X",50,0,50,50)
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

  test("Can Cast") {
    val darkSpell = new ExSpell2(10,10)
    ch1.addSpell(darkSpell)
    ch1.equip(wp2)
    assertEquals(ch1.getMana,100)
    ch1.castSpell(en1,0)
    assertEquals(ch1.getMana,90)
    assertEquals(en1.getHp,40)
  }

  test("Cannot Assign Owned Weapon") {
    interceptMessage[InvalidActionException](
      "An invalid action was found -- Assigning Owned Weapon"
    ) {
      ch1.equip(wp1)
    }
  }
}