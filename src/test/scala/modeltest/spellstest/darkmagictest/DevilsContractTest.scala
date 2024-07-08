package modeltest.spellstest.darkmagictest

import model.armament.concrete.Wand
import model.entities.enemies.Enemy
import model.entities.playablecharacters.concrete.BlackMage
import model.spells.darkmagic.concrete.DevilsContract

class DevilsContractTest extends munit.FunSuite {
  var ch1: BlackMage = _
  var wp1: Wand      = _
  var en1: Enemy     = _
  var sp1: DevilsContract  = _

  override def beforeEach(context: BeforeEach): Unit = {
    ch1 = new BlackMage("X", 10, 10, 10, 10)
    wp1 = new Wand("Y", 15, 10, 15)
    en1 = new Enemy("Z", 20,  2, 19, 10)
    sp1 = new DevilsContract(100, 5)
    ch1.equip(wp1)
    ch1.addSpell(sp1)
  }

  test("Death Stops the spell") {
    ch1.castSpell(en1, 0)
    assertEquals(ch1.getHp,  0)
    assertEquals(en1.getHp, 20)
  }
}
