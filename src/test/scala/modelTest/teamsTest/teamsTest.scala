package modelTest.teamsTest

import model.entities.characters.paladin.Paladin
import model.entities.characters.warrior.Warrior
import model.entities.enemies.enemy.Enemy
import model.teams.enemies.Enemies
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

class EnemiesTest extends munit.FunSuite{
  var p1: Enemies = _
  var p2: Enemies = _
  var ch1: Enemy   = _

  override def beforeEach(context: BeforeEach): Unit = {
    p1 = new Enemies(5)
    p2 = new Enemies(3)
    ch1 = new Enemy("Joe", 10, 20, 30, 40)
  }

  test("Enemy party size") {
    val expected = true
    val actual   = p1.characters.length <= 5
    assertEquals(actual,expected,"No more than 5 characters allowed")
  }

  test("Adding Enemies") {
    assertEquals(p1.characters(0), null, "Party should be initiated without characters")
    p1.addEnemy(ch1)
    assertEquals(p1.characters(0), ch1, "Party should be initiated without characters")
  }
}