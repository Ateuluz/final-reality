package modeltest.armamenttest.bowtest

import exceptions.InvalidHolderException
import model.armament.concrete.Bow
import model.entities.playablecharacters.concrete.{Ninja, Paladin, Warrior, WhiteMage}
import model.entities.playablecharacters.ICharacter
import model.entities.playablecharacters.typetraits.IBowBearer

class BowTest extends munit.FunSuite() {
  var ch1: IBowBearer = _
  var ch2: Paladin = _
  var wp1: Bow = _
  var wp2: Bow = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    ch1 = new Ninja("A", 10, 10, 10)
    wp1 = new Bow("B", 5, 10)
    ch1.equip(wp1)
    wp2 = new Bow("C", 20, 30)
  }

  test("Owner Check") {
    val expected1 = ch1
    val actual1 = wp1.getOwner
    println(expected1)
    println(actual1)
    println(ch1.getWeapon)
    actual1 match {
      case Some(actual: ICharacter) => assertEquals(actual, expected1)
      case _ => fail("The returned instance is not a IBowBearer")
    }
    val expected2 = true
    val actual2 = wp2.getOwner.isEmpty
    assertEquals(actual2,expected2,"The returned instance is not empty")
  }

  test("Can be Equipped") {
    val chAux2 = new Ninja("X", 2, 2, 2)
    val chAux4 = new Warrior("X", 2, 2, 2)
    val chAux5 = new WhiteMage("X", 2, 2, 2, 2)
    val wpAux = new Bow("X", 2, 2)
    try {
      chAux2.equip(wpAux)
      chAux2.unEquip()
      chAux4.equip(wpAux)
      chAux4.unEquip()
      chAux5.equip(wpAux)
      chAux5.unEquip()
    } catch {
      case _: InvalidHolderException => fail("Weapon Is Supposed To Be Assigned to All")
    }
  }
}
