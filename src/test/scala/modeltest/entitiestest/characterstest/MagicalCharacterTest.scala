package modeltest.entitiestest.characterstest

import exceptions.InvalidActionException
import model.armament.concrete.{Bow, Staff}
import model.entities.enemies.Enemy
import model.entities.playablecharacters.concrete.WhiteMage
import model.spells.ISpell
import model.spells.lightmagic.heal.Heal

import scala.collection.mutable.ArrayBuffer

class MagicalCharacterTest extends munit.FunSuite {
  var ch1: WhiteMage = _
  var ch2: WhiteMage = _
  var en1: Enemy     = _
  var wp1: Staff     = _
  var wp2: Bow       = _
  override def beforeEach(context: BeforeEach): Unit = {
    ch1 = new WhiteMage("A", 1, 1, 1, 1)
    ch2 = new WhiteMage("B", 2, 2, 2, 2)
    en1 = new Enemy("X",3,1,3,1)
    wp1 = new Staff("S",4,4,4)
    wp2 = new Bow("T",4,4)
  }

  test("Mana can be Modified") {
    ch1.setMana(2)
    assertEquals(ch1.getMana,2)
  }

  test("Has Spells") {
    assertEquals(ch1.getSpells,ArrayBuffer[ISpell]())
  }

  test("Has Magic Attack") {
    ch1.equip(wp1)
    assertEquals(ch1.getMagicAttack,4)
  }

  test("Learn and Forget Spells") {
    val healSpell = new Heal(1,1)
    ch1.addSpell(healSpell)
    assertEquals(ch1.getSpells,ArrayBuffer[ISpell](
      healSpell
    ))
    ch1.removeSpell(0)
    assertEquals(ch1.getSpells,ArrayBuffer[ISpell]())
  }

  test("Does Not Crash On Bad Index") {
    ch1.removeSpell(0)
    assertEquals(ch1.getSpells, ArrayBuffer[ISpell]())
  }

  test("Cannot Cast Without Weapon") {
    interceptMessage[InvalidActionException](
      "An invalid action was found -- No Weapon equipped"
    ) {
      val healSpell = new Heal(1, 1)
      ch1.addSpell(healSpell)
      ch1.castSpell(ch2, 0)
    }
  }
  test("Cannot Cast With Wrong Weapon") {
    interceptMessage[InvalidActionException](
      "An invalid action was found -- Weapon cannot cast"
    ) {
      val healSpell = new Heal(1, 1)
      ch1.addSpell(healSpell)
      ch1.equip(wp2)
      ch1.castSpell(ch2, 0)
    }
  }
  test("Cannot Cast Without Enough Mana") {
    interceptMessage[InvalidActionException](
      "An invalid action was found -- Not enough mana to cast this spell"
    ) {
      val healSpell = new Heal(1, 1)
      ch1.addSpell(healSpell)
      ch1.equip(wp1)
      ch1.castSpell(ch2, 0)
      ch1.castSpell(ch2, 0)
      ch1.castSpell(ch2, 0)
      ch1.castSpell(ch2, 0)
    }
  }
  test("Cannot Target Dead Ally") {
    interceptMessage[InvalidActionException](
      "An invalid action was found -- Cannot target dead entity"
    ) {
      val healSpell = new Heal(1, 1)
      ch1.addSpell(healSpell)
      ch1.equip(wp1)
      en1.attack(ch2)
      en1.attack(ch2)
      en1.attack(ch2)
      ch1.castSpell(ch2, 0)
    }
  }
  test("Can Cast") {
    val healSpell = new Heal(1,1)
    ch1.addSpell(healSpell)
    ch1.equip(wp1)
    assertEquals(ch1.getMana,1)
    ch1.castSpell(ch2,0)
    assertEquals(ch1.getMana,0)
  }
}
