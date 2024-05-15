package modelTest.armamentTest.swordTest

import exceptions.InvalidHolderException
import model.armament.sword.Sword
import model.entities.characters.blackMage.BlackMage
import model.entities.characters.ninja.Ninja
import model.entities.characters.paladin.Paladin
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
    ch1.equip(wp1)
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

  test("Can be Equipped") {
    val chAux1 = new Paladin("X", 2, 2, 2)
    val chAux2 = new Ninja("X", 2, 2, 2)
    val chAux3 = new BlackMage("X", 2, 2, 2, 2)
    val chAux4 = new Warrior("X", 2, 2, 2)
    val wpAux = new Sword("X", 2, 2)
    try {
      chAux1.equip(wpAux)
      chAux1.unEquip()
      chAux2.equip(wpAux)
      chAux2.unEquip()
      chAux3.equip(wpAux)
      chAux3.unEquip()
      chAux4.equip(wpAux)
      chAux4.unEquip()
    } catch {
      case _: InvalidHolderException => fail("Weapon Is Supposed To Be Assigned to All")
    }
  }
}
