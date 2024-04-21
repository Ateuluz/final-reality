package modelTest.teamsTest

import model.characters.paladin.Paladin
import model.characters.warrior.Warrior
import model.enemies.enemy.Enemy
import model.teams.enemies.Enemies
import model.teams.party.Party


class PartyTest extends munit.FunSuite{
  var tstSbjt1: Party   = _
  var tstSbjt2: Party   = _
  var tstSbjt3: Paladin = _
  var tstSbjt4: Warrior = _

  override def beforeEach(context: BeforeEach): Unit = {
    tstSbjt1 = new Party
    tstSbjt2 = new Party
    tstSbjt3 = new Paladin("Joe", 10, 20, 30)
    tstSbjt4 = new Warrior("Doe", 0, 10, 20)
  }

  test("Party size") {
    val expected = 3
    assertEquals(tstSbjt1.characters.length,expected,"The number of characters allowed should be 3")
  }

  test("Adding Characters") {
    assertEquals(tstSbjt1.characters(0), null, "Party should be initiated without characters")
    tstSbjt1.addCharacter(tstSbjt3)
    assertEquals(tstSbjt1.characters(0), tstSbjt3, "Party should be initiated without characters")
  }

  test("Knowing Defeat") {
    tstSbjt1.addCharacter(tstSbjt4)
    tstSbjt1.addCharacter(tstSbjt4)
    tstSbjt1.addCharacter(tstSbjt4)
    tstSbjt2.addCharacter(tstSbjt3)
    tstSbjt2.addCharacter(tstSbjt3)
    tstSbjt2.addCharacter(tstSbjt3)
    val expected1 = true
    val expected2 = false
    assertEquals(tstSbjt1.isDefeated,expected1,"Should be defeated")
    assertEquals(tstSbjt2.isDefeated,expected2,"Shouldn't be defeated")
  }
}

class EnemiesTest extends munit.FunSuite{
  var tstSbjt1: Enemies = _
  var tstSbjt2: Enemies = _
  var tstSbjt3: Enemy   = _

  override def beforeEach(context: BeforeEach): Unit = {
    tstSbjt1 = new Enemies(5)
    tstSbjt2 = new Enemies(3)
    tstSbjt3 = new Enemy("Joe", 10, 20, 30, 40)
  }

  test("Enemy party size") {
    val expected = true
    val actual   = tstSbjt1.characters.length <= 5
    assertEquals(actual,expected,"No more than 5 characters allowed")
  }

  test("Adding Enemies") {
    assertEquals(tstSbjt1.characters(0), null, "Party should be initiated without characters")
    tstSbjt1.addEnemy(tstSbjt3)
    assertEquals(tstSbjt1.characters(0), tstSbjt3, "Party should be initiated without characters")
  }
}