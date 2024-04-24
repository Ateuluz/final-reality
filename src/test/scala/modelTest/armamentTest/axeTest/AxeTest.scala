package modelTest.armamentTest.axeTest

import model.armament.axe.Axe
import model.entities.characters.{IAxeBearer, ICharacter}
import model.entities.characters.paladin.Paladin
import model.entities.characters.whiteMage.WhiteMage

class AxeTest extends munit.FunSuite() {
  var ch1: IAxeBearer = _
  var ch2: WhiteMage = _
  var wp1: Axe = _
  var wp2: Axe = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    ch1 = new Paladin("A", 10, 10, 10)
    wp1 = new Axe("B", 5, 10)
    ch1.requestBindWeapon(wp1)
    wp2 = new Axe("C", 20, 30)
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
