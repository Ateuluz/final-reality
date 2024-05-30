package modeltest.armamenttest.wandtest

import exceptions.InvalidHolderException
import model.armament.wand.Wand
import model.entities.characters.blackmage.BlackMage
import model.entities.characters.ninja.Ninja
import model.entities.characters.warrior.Warrior
import model.entities.characters.{ACharacter, IWandUser}
import model.entities.characters.whitemage.WhiteMage

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

  test("Can be Equipped") {
    val chAux1 = new Ninja("X", 2, 2, 2)
    val chAux2 = new WhiteMage("X", 2, 2, 2, 2)
    val chAux3 = new BlackMage("X", 2, 2, 2, 2)
    val wpAux = new Wand("X", 2, 2, 2)
    try {
      chAux1.equip(wpAux)
      chAux1.unEquip()
      chAux2.equip(wpAux)
      chAux2.unEquip()
      chAux3.equip(wpAux)
      chAux3.unEquip()
    } catch {
      case _: InvalidHolderException => fail("Weapon Is Supposed To Be Assigned to All")
    }
  }
}
