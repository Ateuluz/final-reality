package model.spells.lightmagic.selectionstrategy

import model.entities.enemies.IEnemy
import model.teams.ITeam
import model.turnscheduler.ITurnScheduler

/** Ateuluz
 *
 * Just return foes
 */
class EnemyTargetSelectionStrategy extends ITargetSelectionStrategy {
  override def getTargets(turnScheduler: ITurnScheduler): ITeam[IEnemy] = {
    turnScheduler.enemyTeam.get
  }
}