package modelTest.entitiesTest

import exceptions.InvalidStatException
import model.armament.sword.Sword
import model.entities.characters.paladin.Paladin
import model.entities.enemies.enemy.Enemy

class EntityTest extends munit.FunSuite {
  var ch1: Paladin = _
  var ch2: Paladin = _
  var en1: Enemy   = _
  var wp1: Sword   = _
  var wp2: Sword   = _
  var name: String   = _
  var threshold: Int = _
  var value: Int     = _
  override def beforeEach(context: BeforeEach): Unit = {
    ch1 = new Paladin("A", 1, 1, 1)
    ch2 = new Paladin("B", 2, 2, 2)
    wp1 = new Sword("C", 1, 1)
    wp2 = new Sword("D", 3, 3)
    en1 = new Enemy("E", 2, 2, 3, 2)
    ch1.requestBindWeapon(wp1)
    ch2.requestBindWeapon(wp2)
  }

  test("Have Name") {
    assertEquals(ch1.getName,"A")
  }
  test("Have Defense") {
    assertEquals(ch1.getDefense,1)
  }

  test("Attack, Defend and NonNegative Health") {
    ch1.attack(ch2)
    assertEquals(ch2.getHp, 2)
    ch2.attack(ch1)
    assertEquals(ch1.getHp, 0)
    en1.attack(en1)
    assertEquals(en1.getHp, 1)
  }

  test("Limited Variable Ranges") {
    this.name = "HP"
    this.threshold = 0
    this.value = -1
    interceptMessage[InvalidStatException](
      s"An invalid stat was found -- ${this.name} should be at least ${this.threshold} but was ${this.value}"
    ) {
      ch1 = new Paladin("Joe", -1, 1, 1)
    }
    this.name = "Defense"
    this.threshold = 0
    this.value = -1
    interceptMessage[InvalidStatException](
      s"An invalid stat was found -- ${this.name} should be at least ${this.threshold} but was ${this.value}"
    ) {
      ch1 = new Paladin("Joe", 1, -1, 1)
    }
    this.name = "Weight"
    this.threshold = 1
    this.value = -1
    interceptMessage[InvalidStatException](
      s"An invalid stat was found -- ${this.name} should be at least ${this.threshold} but was ${this.value}"
    ) {
      ch1 = new Paladin("Joe", 1, 1, -1)
    }
  }
}
