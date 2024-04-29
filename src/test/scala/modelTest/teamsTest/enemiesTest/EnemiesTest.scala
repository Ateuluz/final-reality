package modelTest.teamsTest.enemiesTest

import model.entities.IEntity
import model.entities.enemies.enemy.Enemy
import model.teams.enemies.Enemies

import scala.collection.mutable.ArrayBuffer

class EnemiesTest extends munit.FunSuite {
  var team1: Enemies = _
  var en1: Enemy = _
  var en2: Enemy = _
  var en3: Enemy = _
  override def beforeEach(context: BeforeEach): Unit = {
    en1 = new Enemy("A", 1, 1, 1, 1)
    en2 = new Enemy("B", 1, 1, 1, 1)
    en3 = new Enemy("C", 1, 1, 1, 1)
  }

  test("Can Add Members") {
    team1 = new Enemies(en1, en2)
    val expected = ArrayBuffer[IEntity](en1,en2,en3)
    team1.addMember(en3)
    assertEquals(team1.getMembers, expected)
  }
}