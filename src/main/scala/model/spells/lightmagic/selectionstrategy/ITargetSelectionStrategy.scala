package model.spells.lightmagic.selectionstrategy

import model.entities.IEntity
import model.teams.ITeam
import model.turnscheduler.ITurnScheduler

/** Ateuluz
 *
 * Just return an arbitrary team
 */
trait ITargetSelectionStrategy {
  def getTargets(turnScheduler: ITurnScheduler): ITeam[_ <: IEntity]
}
