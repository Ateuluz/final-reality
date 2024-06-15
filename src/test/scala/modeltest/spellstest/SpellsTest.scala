package modeltest.spellstest

import exceptions.InvalidCasterException
import model.armament.wand.Wand
import model.entities.playablecharacters.blackmage.BlackMage
import model.entities.playablecharacters.whitemage.WhiteMage
import model.entities.enemies.enemy.Enemy
import model.spells.basicmagic.concrete.Fireball
import model.spells.darkmagic.concrete.DevilsContract
import model.spells.lightmagic.heal.Heal

class SpellsTest extends munit.FunSuite {
  var ch1: WhiteMage = _
  var ch2: BlackMage = _
  var wp1: Wand      = _
  var wp2: Wand      = _
  var ch3: WhiteMage = _
  var en1: Enemy     = _
  var sp1: Heal      = _
  var sp2: DevilsContract  = _
  var sp3: Fireball  = _

  override def beforeEach(context: BeforeEach): Unit = {
    ch1 = new WhiteMage("X", 10, 10, 10, 10)
    ch2 = new BlackMage("X", 10, 10, 10, 10)
    wp1 = new Wand("Y", 15, 10, 5)
    wp2 = new Wand("Y", 12, 10, 5)
    ch3 = new WhiteMage("Z", 20,  2, 10, 10)
    en1 = new Enemy("Z", 20,  2, 10, 10)
    sp1 = new Heal(5, 5)
    sp2 = new DevilsContract(5, 5)
    sp3 = new Fireball(5, 5)
    ch1.equip(wp1)
    ch2.equip(wp2)
  }

  test("Casting restrictions") {
    interceptMessage[InvalidCasterException](
      "An invalid spell caster was found -- Spell cannot be cast by a white mage"
    ){
      ch1.addSpell(sp2)
      ch1.castSpell(en1, 0)
    }
    interceptMessage[InvalidCasterException](
      "An invalid spell caster was found -- Spell cannot be cast by a black mage"
    ){
      ch2.addSpell(sp1)
      ch2.castSpell(ch1, 0)
    }
  }

  test("Can Be Casted") {
    ch1.addSpell(sp3)
    ch2.addSpell(sp3)
    ch1.castSpell(en1, 0)
    assertEquals(en1.getHp, 20 - ((5 + 5) - (2 / 2))) // Defense is half for spells
    ch2.castSpell(en1, 0)
    assertEquals(en1.getHp, 11 - ((5 + 5) - (2 / 2))) // Defense is half for spells
  }
}
