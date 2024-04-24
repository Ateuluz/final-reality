package modelTest.teamsTest.enemiesTest

import model.entities.enemies.enemy.Enemy
import model.teams.enemies.Enemies

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
