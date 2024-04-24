package modelTest.armamentTest.swordTest

import model.armament.sword.Sword
import model.entities.characters.{ICharacter, ISwordBearer}
import model.entities.characters.warrior.Warrior
import model.entities.characters.whiteMage.WhiteMage

class SwordTest extends munit.FunSuite() {
  var ch1: ISwordBearer = _
  var ch2: WhiteMage = _
  var wp1: Sword = _
  var wp2: Sword = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    ch1 = new Warrior("A", 10, 10, 10)
    wp1 = new Sword("B", 5, 10)
    ch1.requestBindWeapon(wp1)
    wp2 = new Sword("C", 20, 30)
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
    ch2 = new WhiteMage("X", 2, 2, 2, 2)
    ch2.requestBindWeapon(wp2)
    assertEquals(wp2.getOwner,None)
  }
}
