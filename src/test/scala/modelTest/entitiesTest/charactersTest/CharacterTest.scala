package modelTest.entitiesTest.charactersTest

import exceptions.InvalidStatException
import model.armament.IWeapon
import model.armament.sword.Sword
import model.entities.characters.paladin.Paladin
import model.entities.characters.warrior.Warrior
import model.entities.characters.whiteMage.WhiteMage

class CharacterTest extends munit.FunSuite{
  var ch1: Paladin   = _
  var ch2: WhiteMage = _
  var ch3: Warrior   = _
  var wp1: Sword     = _
  var wp2: Sword     = _
  var name: String   = _
  var threshold: Int = _
  var value: Int     = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    wp1 = new Sword("Excal", 10, 20)
    ch1 = new Paladin("John", 80, 20, 5)
    ch1.equip(wp1)
    ch2 = new WhiteMage("Doe", 200, 10, 25, 150)
    wp2 = new Sword("Nex", 20, 200)
  }

  //test("Limited Variable Ranges") {
  //  var allowNegativeHP: Boolean = false
  //  var allowNegativeDefense: Boolean = false
  //  var allowNegativeWeight: Boolean = false
  //  var allowNegativeMana: Boolean = false
  //  try {
  //    ch3 = new Warrior("Joe", -1, 1, 1)
  //    allowNegativeHP = true
  //  } catch {
  //    case _: java.lang.IllegalArgumentException =>
  //  }
  //  try {
  //    ch3 = new Warrior("Joe", 1, -1, 1)
  //    allowNegativeDefense = true
  //  } catch {
  //    case _: java.lang.IllegalArgumentException =>
  //  }
  //  try {
  //    ch3 = new Warrior("Joe", 1, 1, -1)
  //    allowNegativeWeight = true
  //  } catch {
  //    case _: java.lang.IllegalArgumentException =>
  //  }
  //  try {
  //    ch2 = new WhiteMage("Joe", 1, 1, 1, -1)
  //    allowNegativeMana = true
  //  } catch {
  //    case _: java.lang.IllegalArgumentException =>
  //  }
  //  assertEquals(allowNegativeHP,false,"Negative HP shouldn't be allowed")
  //  assertEquals(allowNegativeDefense,false,"Negative Defense shouldn't be allowed")
  //  assertEquals(allowNegativeWeight,false,"Negative or zero Weight shouldn't be allowed") // Speed related
  //  assertEquals(allowNegativeMana,false,"Negative Mana shouldn't be allowed")
  //}

  test("Limited Variable Ranges") {
    this.name = "Mana"
    this.threshold = 0
    this.value = -1
    interceptMessage[InvalidStatException](
      s"An invalid stat was found -- ${this.name} should be at least ${this.threshold} but was ${this.value}"
    ) {
      ch2 = new WhiteMage("Joe", 1, 1, 1, -1)
    }
  }

  test("Cannot Assign Owned Weapon") {
    ch2.equip(wp1)
    assertEquals(ch2.getWeapon,None)
  }

  test("Swap Weapon") {
    ch1.equip(wp2)
    assertEquals(wp1.getOwner,None)
    ch1.getWeapon match {
      case Some(actual: IWeapon) => assertEquals(actual, wp2)
      case _ => fail("The returned instance is not the intended Weapon")
    }
  }

  test("Can't Attack Without Weapon") {
    val expected = ch1.getHp
    ch2.attack(ch1)
    assertEquals(ch1.getHp,expected)
  }
}