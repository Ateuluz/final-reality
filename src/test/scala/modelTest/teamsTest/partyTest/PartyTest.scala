package modelTest.teamsTest.partyTest

import model.entities.characters.paladin.Paladin
import model.entities.characters.warrior.Warrior
import model.teams.party.Party

class PartyTest extends munit.FunSuite{
  var p1: Party   = _
  var p2: Party   = _
  var ch1: Paladin = _
  var ch2: Warrior = _

  override def beforeEach(context: BeforeEach): Unit = {
    p1 = new Party
    p2 = new Party
    ch1 = new Paladin("Joe", 10, 20, 30)
    ch2 = new Warrior("Doe", 0, 10, 20)
  }

  test("Party size") {
    val expected = 3
    assertEquals(p1.getCharacters.length,expected,"The number of characters allowed should be 3")
  }

  test("Adding Characters") {
    assertEquals(p1.getCharacters(0), null, "Party should be initiated without characters")
    p1.addCharacter(ch1)
    assertEquals(p1.getCharacters(0), ch1, "Party should be initiated without characters")
  }

  test("Knowing Defeat") {
    p1.addCharacter(ch2)
    p1.addCharacter(ch2)
    p1.addCharacter(ch2)
    p2.addCharacter(ch1)
    p2.addCharacter(ch1)
    p2.addCharacter(ch1)
    val expected1 = true
    val expected2 = false
    assertEquals(p1.isDefeated,expected1,"Should be defeated")
    assertEquals(p2.isDefeated,expected2,"Shouldn't be defeated")
  }
}