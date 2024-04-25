package modelTest.entitiesTest

import model.armament.sword.Sword
import model.entities.characters.paladin.Paladin

class EntityTest extends munit.FunSuite {
  var ch1: Paladin = _
  var ch2: Paladin = _
  var wp1: Sword   = _
  var wp2: Sword   = _
  override def beforeEach(context: BeforeEach): Unit = {
    ch1 = new Paladin("A", 1, 1, 1)
    ch2 = new Paladin("B", 2, 2, 2)
    wp1 = new Sword("C", 1, 1)
    wp2 = new Sword("D", 3, 3)
    ch1.requestBindWeapon(wp1)
    ch2.requestBindWeapon(wp2)
  }

  test("Have name") {
    assertEquals(ch1.getName,"A")
  }

  test("Can Alter Hp") {
    ch1.setHp(2)
    assertEquals(ch1.getHp,2)
  }

  test("Attack, Defend and NonNegative Health") {
    ch1.attack(ch2)
    assertEquals(ch2.getHp, 2)
    ch2.attack(ch1)
    assertEquals(ch1.getHp, 0)
  }
}
