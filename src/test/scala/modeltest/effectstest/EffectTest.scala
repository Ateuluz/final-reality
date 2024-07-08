package modeltest.effectstest

import model.armament.concrete.{Staff, Wand}
import model.effects.AEffect
import model.entities.enemies.Enemy
import model.entities.playablecharacters.concrete.{BlackMage, WhiteMage}
import model.spells.basicmagic.concrete.Fireball
import model.spells.darkmagic.concrete.DevilsContract

class EffectTest extends munit.FunSuite {
  var ch1: WhiteMage = _
  var ch2: BlackMage = _
  var en1: Enemy = _
  var wp1: Staff = _
  var wp2: Wand = _
  var sp1: Fireball = _
  var sp2: DevilsContract = _

  override def beforeEach(context: BeforeEach): Unit = {
    ch1 = new WhiteMage("X", 10, 10, 10, 10)
    ch2 = new BlackMage("X", 10, 10, 10, 10)
    en1 = new Enemy("Y", 10, 4, 10, 10)
    wp1 = new Staff("Z", 10, 10, 10)
    wp2 = new Wand("Z", 10, 10, 10)
    sp1 = new Fireball(5, 1)
    sp2 = new DevilsContract(5, 1)
  }

  test("Duration Set") {
    class X1(dur: Int) extends AEffect(1,dur)
    ch2.effectsAdd(new X1(1))
    ch2.effectsApply()
  }

  test("Paralyse Renders Entity Action Unable") {
    en1.actionAble = true
    ch2.addSpell(sp2) // id: 0
    ch2.equip(wp2)
    ch2.castSpell(en1,0)
    en1.effectsApply()
    assert((!en1.actionAble) && (en1.getHp > 0))
  }

  test("Poison Reduces HP") {
    en1.actionAble = true
    ch2.addSpell(sp2) // id: 0
    ch2.equip(wp2)
    ch2.castSpell(en1,0)
    assertEquals(en1.getHp, 7)
    en1.effectsApply()
    assertEquals(en1.getHp, 4)
  }
}
