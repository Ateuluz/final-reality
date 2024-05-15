package modelTest.armamentTest

import exceptions.{InvalidHolderException, InvalidStatException}
import model.armament.axe.Axe
import model.armament.bow.Bow
import model.armament.staff.Staff
import model.armament.sword.Sword
import model.armament.wand.Wand
import model.entities.characters.blackMage.BlackMage
import model.entities.characters.ninja.Ninja
import model.entities.characters.paladin.Paladin
import model.entities.characters.warrior.Warrior
import model.entities.characters.whiteMage.WhiteMage

class WeaponTest extends munit.FunSuite{
  var wp1: Sword = _
  var wp2: Staff = _
  var name: String   = _
  var threshold: Int = _
  var value: Int     = _

  override def beforeEach(context: BeforeEach): Unit = {
    wp1 = new Sword("A", 5, 10)
    wp2 = new Staff("B", 20, 30, 25)
  }

  test("Limited Variable Ranges") {
    this.name = "Attack"
    this.threshold = 1
    this.value = -1
    interceptMessage[InvalidStatException](
      s"An invalid stat was found -- ${this.name} should be at least ${this.threshold} but was ${this.value}"
    ) {
      new Sword("C", -1, 1)
    }
    this.name = "Weight"
    this.threshold = 0
    this.value = -1
    interceptMessage[InvalidStatException](
      s"An invalid stat was found -- ${this.name} should be at least ${this.threshold} but was ${this.value}"
    ) {
      new Sword("D", 1, -1)
    }
  }
  //test("Limited Variable Ranges") {
  //  var allowNegativeHP: Boolean = false
  //  var allowNegativeWeight: Boolean = false
  //  var allowNegativeMagicAttack: Boolean = false
  //  try {
  //    wp1 = new Sword("A", -1, 1)
  //    allowNegativeHP = true
  //  } catch {
  //    case _: java.lang.IllegalArgumentException =>
  //  }
  //  try {
  //    wp1 = new Sword("A", 1, -1)
  //    allowNegativeWeight = true
  //  } catch {
  //    case _: java.lang.IllegalArgumentException =>
  //  }
  //  try {
  //    wp2 = new Staff("B", 1, 1, -1)
  //    allowNegativeMagicAttack = true
  //  } catch {
  //    case _: java.lang.IllegalArgumentException =>
  //  }
  //  assertEquals(allowNegativeHP,false,"Negative HP shouldn't be allowed")
  //  assertEquals(allowNegativeWeight,false,"Negative or zero Weight shouldn't be allowed") // Speed related
  //  assertEquals(allowNegativeMagicAttack,false,"Negative MagicAttack shouldn't be allowed")
  //}

  //test("Out of Range Attributes set to Arbitrary") {
  //  wp1 = new Sword("Joe", -1, 1)
  //  assertEquals(wp1.getAttack,1)
  //  wp1 = new Sword("Joe", 1, -1)
  //  assertEquals(wp1.getWeight,0)
  //  wp2 = new Staff("Joe", 1, 1, -1)
  //  assertEquals(wp2.getMagicAttack,1)
  //}

  test("Has Name") {
    assertEquals(wp1.getName,"A")
  }

  test("Has MagicAttack") {
    assertEquals(wp2.getMagicAttack,25)
  }

  test("Non User Cannot Equip Extended") {
    val chAux1 = new Warrior("X", 2, 2, 2)
    val chAux2 = new Paladin("X", 2, 2, 2)
    val chAux3 = new Ninja("X", 2, 2, 2)
    val chAux4 = new WhiteMage("X", 2, 2, 2, 2)
    val chAux5 = new BlackMage("X", 2, 2, 2, 2)

    val wpAux1 = new Sword("X", 2, 2)
    val wpAux2 = new Axe("X", 2, 2)
    val wpAux3 = new Bow("X", 2, 2)
    val wpAux4 = new Wand("X", 2, 2, 2)
    val wpAux5 = new Staff("X", 2, 2, 2)

    try {
      chAux4.equip(wpAux1)
    } catch {
      case _: InvalidHolderException =>
      case _ => fail("The Weapon Is Not Supposed To Be Assigned")
    }
    try {
      chAux3.equip(wpAux2)
    } catch {
      case _: InvalidHolderException =>
      case _ => fail("The Weapon Is Not Supposed To Be Assigned")
    }
    try {
      chAux5.equip(wpAux3)
    } catch {
      case _: InvalidHolderException =>
      case _ => fail("The Weapon Is Not Supposed To Be Assigned")
    }
    try {
      chAux1.equip(wpAux4)
    } catch {
      case _: InvalidHolderException =>
      case _ => fail("The Weapon Is Not Supposed To Be Assigned")
    }
    try {
      chAux2.equip(wpAux5)
    } catch {
      case _: InvalidHolderException =>
      case _ => fail("The Weapon Is Not Supposed To Be Assigned")
    }
  }
}