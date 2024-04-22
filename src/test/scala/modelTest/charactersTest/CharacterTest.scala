package modelTest.charactersTest

import model.armament.sword.Sword
import model.entities.characters.paladin.Paladin
import model.entities.characters.warrior.Warrior
import model.entities.characters.whiteMage.WhiteMage

class CharacterTest extends munit.FunSuite{
  var tstSbjt1: Paladin   = _
  var tstSbjt2: WhiteMage = _
  var tstSbjt3: Warrior   = _
  var tstObjt1: Sword     = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstObjt1 = new Sword("Excal", 10, 20, tstSbjt1)
    tstSbjt1 = new Paladin("John", 80, 20, 5, tstObjt1)
    tstSbjt2 = new WhiteMage("Doe", 200, 10, 25, 150)
  }

  test("Limited Variable Ranges") {
    var allowNegativeHP: Boolean = false
    var allowNegativeDefense: Boolean = false
    var allowNegativeWeight: Boolean = false
    var allowNegativeMana: Boolean = false
    try {
      tstSbjt3 = new Warrior("Joe", -1, 1, 1)
      allowNegativeHP = true
    } catch {
      case _: java.lang.IllegalArgumentException =>
    }
    try {
      tstSbjt3 = new Warrior("Joe", 1, -1, 1)
      allowNegativeDefense = true
    } catch {
      case _: java.lang.IllegalArgumentException =>
    }
    try {
      tstSbjt3 = new Warrior("Joe", 1, 1, -1)
      allowNegativeWeight = true
    } catch {
      case _: java.lang.IllegalArgumentException =>
    }
    try {
      tstSbjt2 = new WhiteMage("Joe", 1, 1, 1, -1)
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