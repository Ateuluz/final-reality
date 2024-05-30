package modeltest.entitiestest.characterstest

import exceptions.{InvalidActionException, InvalidHolderException, InvalidStatException}
import model.armament.IWeapon
import model.armament.sword.Sword
import model.entities.playablecharacters.paladin.Paladin
import model.entities.playablecharacters.warrior.Warrior
import model.entities.playablecharacters.whitemage.WhiteMage
import model.entities.enemies.enemy.Enemy

class CharacterTest extends munit.FunSuite{
  var ch1: Paladin   = _
  var ch2: WhiteMage = _
  var ch3: Warrior   = _
  var en1: Enemy     = _
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
    en1 = new Enemy("X", 11, 10, 100, 10)
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

  test("Swap Weapon") {
    assertEquals(wp1.getOwner.get,ch1)
    ch1.equip(wp2)
    assertEquals(wp1.getOwner,None)
    ch1.getWeapon match {
      case Some(actual: IWeapon) => assertEquals(actual, wp2)
      case _ => fail("The returned instance is not the intended Weapon")
    }
  }

  test("Can't Attack Without Weapon") {
    interceptMessage[InvalidActionException](
      s"An invalid action was found -- Cannot get attack of Character without Weapon."
    ) {
      ch2.attack(en1)
    }
  }

  test("Can't Attack Ally") {
    interceptMessage[InvalidActionException](
      s"An invalid action was found -- Character cannot attack this entity."
    ) {
      ch1.attack(ch2)
    }
  }

  test("Can Attack Enemy") {
    try {
      ch1.attack(en1)
    } catch {
      case _: InvalidActionException => fail("The Character Is Supposed Attack")
      case _ =>
        assertEquals(
          en1.getHp,
          1,
          "Incorrect damage assignation"
        )
    }
  }
}