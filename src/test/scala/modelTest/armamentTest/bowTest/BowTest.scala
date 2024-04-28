package modelTest.armamentTest.bowTest

import model.armament.bow.Bow
import model.entities.characters.{IBowBearer, ICharacter}
import model.entities.characters.ninja.Ninja
import model.entities.characters.paladin.Paladin

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

  test("Non User cannot Equip") {
    ch2 = new Paladin("X", 2, 2, 2)
    ch2.equip(wp2)
    assertEquals(wp2.getOwner,None)
  }
}
