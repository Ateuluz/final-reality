package model.entities.playablecharacters.whitemage

import exceptions.InvalidActionException
import model.armament.IWeapon
import model.entities.IEntity
import model.entities.playablecharacters.{AMagicalCharacter, IBowBearer, IStaffUser, IWandUser}

/** Ateuluz Creating instance of WhiteMage
 *
 * @param name    Name given to the mage
 * @param hp      Health points
 * @param defense Defense value
 * @param weight  Weight of the mage
 * @param mana    Mana capacity
 */
class WhiteMage (
                  name: String,
                  hp: Int,
                  defense: Int,
                  weight: Int,
                  mana: Int
                ) extends AMagicalCharacter(name,hp,defense,weight,mana)
                    with IBowBearer with IWandUser with IStaffUser {

  override def equip(wp: IWeapon): Unit = {
    if (wp.getOwner.isEmpty) {
      unEquip()
      wp.equipToWhiteMage(this)
      setWeapon(wp)
    } else {
      throw new InvalidActionException("Assigning Owned Weapon")
    }
  }

  /** Ateuluz
   *
   * If all conditions for throwing are met, the spell will be cast.
   * If the casting throws no exceptions, mana will be discounted.
   *
   * @param target Whom to cast the spell on
   * @param spellID What spell to cast
   */
  override def castSpell(target: IEntity, spellID: Int): Unit = {
    canCastSpell(target, spellID) // Just a check
    getSpells(spellID).castByWhiteMage(this, target)
    this.setMana(
      this.getMana - getSpells(spellID).getConsumption
    )
  }
}
