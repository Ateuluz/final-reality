package modeltest.entitiestest

import exceptions.{InvalidHandleException, InvalidStatException}
import model.armament.concrete.Sword
import model.effects.concrete.Poisoned
import model.entities.enemies.Enemy
import model.entities.playablecharacters.concrete.Paladin

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
    ch1.equip(wp1)
    ch2.equip(wp2)
  }

  test("Have Name") {
    assertEquals(ch1.getName,"A")
  }
  test("Have Defense") {
    assertEquals(ch1.getDefense,1)
  }
  test("Have HP Max") {
    assertEquals(ch1.getHpMax,1)
  }

  test("Attack, Defend and NonNegative Health") {
    ch2.attack(en1)             // Atk c->e
    assertEquals(en1.getHp, 1)
    en1.attack(ch1)             // Atk e->c | NNegHP
    assertEquals(ch1.getHp, 0)
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


  test("Can Be Healed") {
    en1.attack(ch2)
    assertEquals(ch2.getHp,1)
    ch2.beHealed(1)
    assertEquals(ch2.getHp,2)
    ch2.beHealed(1)
    assertEquals(ch2.getHp,2)
  }

  test("Dead Cannot Be Healed") {
    en1.attack(ch1)
    assertEquals(ch1.getHp,0)
    ch1.beHealed(1)
    assertEquals(ch1.getHp,0)
  }

  test("Action Able Getter works") {
    ch1.actionAble = false
    assert(!ch1.actionAble)

    ch1.actionAble = true
    assert(ch1.actionAble)

    en1.attack(ch1)

    ch1.actionAble = false
    assert(!ch1.actionAble)

    ch1.actionAble = true
    assert(!ch1.actionAble)
  }

  test("Unaffected by Random Effect") {
    interceptMessage[InvalidHandleException](
      "An invalid user action was found -- Effect Not Found"
    ) {
      ch1.defendFromEffect(new Poisoned(999))
    }
  }

  test("Cannot Remove Random Effect") {
    interceptMessage[InvalidHandleException](
      "An invalid user action was found -- Effect Not Found"
    ) {
      ch1.effectsRemove(new Poisoned(999))
    }
  }
}
