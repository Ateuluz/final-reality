package modeltest.spellstest.lightmagictest

import model.entities.enemies.enemy.Enemy
import model.entities.playablecharacters.warrior.Warrior
import model.spells.lightmagic.selectionstrategy.{AllyTargetSelectionStrategy, EnemyTargetSelectionStrategy}
import model.teams.enemies.Enemies
import model.teams.party.Party
import model.turnscheduler.TurnScheduler

class TargetSelectionStrategy extends munit.FunSuite {
  var ats: AllyTargetSelectionStrategy  = _
  var ets: EnemyTargetSelectionStrategy = _
  var trn: TurnScheduler                = _
  var p  : Party                        = _
  var e  : Enemies                      = _

  override def beforeEach(context: BeforeEach): Unit = {
    ats = new AllyTargetSelectionStrategy
    ets = new EnemyTargetSelectionStrategy
    trn = new TurnScheduler
    p = new Party(
      new Warrior("A", 1, 1, 1),
      new Warrior("B", 1, 1, 1),
      new Warrior("C", 1, 1, 1),
    )
    e = new Enemies(
      new Enemy("A", 1, 1, 1, 1),
      new Enemy("B", 1, 1, 1, 1),
      new Enemy("C", 1, 1, 1, 1),
    )
    trn.party     = p
    trn.enemyTeam = e
  }

  test("Return are correct") {
    assertEquals(ats.getTargets(trn), trn.party    .get)
    assertEquals(ets.getTargets(trn), trn.enemyTeam.get)
  }
}
