package modelTest.armamentTest.wandTest

import model.armament.wand.Wand
import model.entities.characters.warrior.Warrior
import model.entities.characters.{ICharacter, IWandUser}
import model.entities.characters.whiteMage.WhiteMage

class WandTest extends munit.FunSuite() {
  var ch1: IWandUser = _
  var ch2: Warrior = _
  var wp1: Wand = _
  var wp2: Wand = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    ch1 = new WhiteMage("A", 10, 10, 10, 10)
    wp1 = new Wand("B", 5, 10, 5)
    ch1.equip(wp1)
    wp2 = new Wand("C", 20, 30, 10)
  }

  test("Owner Check") {
    val expected1 = ch1
    val actual1 = wp1.getOwner
    actual1 match {
      case Some(actual: IWandUser) => assertEquals(actual, expected1)
      case _ => fail("The returned instance is not a WandUser")
    }
    val expected2 = true
    val actual2 = wp2.getOwner.isEmpty
    assertEquals(actual2,expected2,"The returned instance is not empty")
  }

  test("Non User cannot Equip") {
    ch2 = new Warrior("X", 2, 2, 2)
    ch2.equip(wp2)
    assertEquals(wp2.getOwner,None)
  }
}
