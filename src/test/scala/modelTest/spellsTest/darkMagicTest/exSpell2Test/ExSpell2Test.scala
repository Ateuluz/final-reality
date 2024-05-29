package modelTest.spellsTest.darkMagicTest.exSpell2Test

import model.armament.wand.Wand
import model.entities.characters.blackMage.BlackMage
import model.entities.characters.whiteMage.WhiteMage
import model.entities.enemies.enemy.Enemy
import model.spells.darkMagic.exSpell2.ExSpell2

class ExSpell2Test extends munit.FunSuite {
  var ch1: BlackMage = _
  var wp1: Wand      = _
  var en1: Enemy     = _
  var sp1: ExSpell2  = _

  override def beforeEach(context: BeforeEach): Unit = {
    ch1 = new BlackMage("X", 10, 10, 10, 10)
    wp1 = new Wand("Y", 15, 10, 15)
    en1 = new Enemy("Z", 20,  2, 19, 10)
    sp1 = new ExSpell2(100, 5)
    ch1.equip(wp1)
    ch1.addSpell(sp1)
  }

  test("Death Stops the spell") {
    ch1.castSpell(en1, 0)
    assertEquals(ch1.getHp,  0)
    assertEquals(en1.getHp, 20)
  }
}
