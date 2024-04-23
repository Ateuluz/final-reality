package modelTest.entitiesTest.charactersTest

import model.armament.sword.Sword
import model.entities.characters.paladin.Paladin
import model.entities.characters.warrior.Warrior
import model.entities.characters.whiteMage.WhiteMage

class CharacterTest extends munit.FunSuite{
  var ch1: Paladin   = _
  var ch2: WhiteMage = _
  var ch3: Warrior   = _
  var wp1: Sword     = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    wp1 = new Sword("Excal", 10, 20)
    ch1 = new Paladin("John", 80, 20, 5)
    ch1.setWeapon(wp1)
    ch2 = new WhiteMage("Doe", 200, 10, 25, 150)
  }

  test("Limited Variable Ranges") {
    var allowNegativeHP: Boolean = false
    var allowNegativeDefense: Boolean = false
    var allowNegativeWeight: Boolean = false
    var allowNegativeMana: Boolean = false
    try {
      ch3 = new Warrior("Joe", -1, 1, 1)
      allowNegativeHP = true
    } catch {
      case _: java.lang.IllegalArgumentException =>
    }
    try {
      ch3 = new Warrior("Joe", 1, -1, 1)
      allowNegativeDefense = true
    } catch {
      case _: java.lang.IllegalArgumentException =>
    }
    try {
      ch3 = new Warrior("Joe", 1, 1, -1)
      allowNegativeWeight = true
    } catch {
      case _: java.lang.IllegalArgumentException =>
    }
    try {
      ch2 = new WhiteMage("Joe", 1, 1, 1, -1)
      allowNegativeMana = true
    } catch {
      case _: java.lang.IllegalArgumentException =>
    }
    assertEquals(allowNegativeHP,false,"Negative HP shouldn't be allowed")
    assertEquals(allowNegativeDefense,false,"Negative Defense shouldn't be allowed")
    assertEquals(allowNegativeWeight,false,"Negative or zero Weight shouldn't be allowed") // Speed related
    assertEquals(allowNegativeMana,false,"Negative Mana shouldn't be allowed")
  }
}