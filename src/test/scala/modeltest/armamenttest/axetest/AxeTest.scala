package modeltest.armamenttest.axetest

import exceptions.InvalidHolderException
import model.armament.axe.Axe
import model.entities.characters.{IAxeBearer, ICharacter}
import model.entities.characters.paladin.Paladin
import model.entities.characters.warrior.Warrior
import model.entities.characters.whitemage.WhiteMage

class AxeTest extends munit.FunSuite() {
  var ch1: IAxeBearer = _
  var ch2: WhiteMage = _
  var wp1: Axe = _
  var wp2: Axe = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    ch1 = new Paladin("A", 10, 10, 10)
    wp1 = new Axe("B", 5, 10)
    ch1.equip(wp1)
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

  test("Can be Equipped") {
    val chAux1 = new Paladin("X", 2, 2, 2)
    val chAux4 = new Warrior("X", 2, 2, 2)
    val wpAux = new Axe("X", 2, 2)
    try {
      chAux1.equip(wpAux)
      chAux1.unEquip()
      chAux4.equip(wpAux)
      chAux4.unEquip()
    } catch {
      case _: InvalidHolderException => fail("Weapon Is Supposed To Be Assigned to All")
    }
  }
}
