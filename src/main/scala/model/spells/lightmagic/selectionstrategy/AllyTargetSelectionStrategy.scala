package model.spells.lightmagic.selectionstrategy

import model.entities.playablecharacters.ICharacter
import model.teams.ITeam
import model.turnscheduler.ITurnScheduler

/** Ateuluz
 *
 * Just return allies
 */
class AllyTargetSelectionStrategy extends ITargetSelectionStrategy {
  override def getTargets(turnScheduler: ITurnScheduler): ITeam[ICharacter] = {
    turnScheduler.party.get
  }
}