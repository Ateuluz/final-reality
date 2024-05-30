package model.entities.characters.blackMage

import exceptions.InvalidActionException
import model.armament.IWeapon
import model.entities.IEntity
import model.entities.characters.{AMagicalCharacter, IStaffUser, ISwordBearer, IWandUser}

/** Ateuluz Creating instance of BlackMage
 *
 * @param name    Name given to the mage
 * @param hp      Health points
 * @param defense Defense value
 * @param weight  Weight of the mage
 * @param mana    Mana capacity
 */
class BlackMage (
                  name: String,
                  hp: Int,
                  defense: Int,
                  weight: Int,
                  mana: Int
                ) extends AMagicalCharacter(name,hp,defense,weight,mana)
                    with ISwordBearer with IWandUser with IStaffUser {

  override def equip(wp: IWeapon): Unit = {
    if (wp.getOwner.isEmpty) {
      unEquip()
      wp.equipToBlackMage(this)
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
    canCastSpell(target, spellID)
    getSpells(spellID).castByBlackMage(this, target)
    setMana(
      getMana-getSpells(spellID).getConsumption
    )
  }
}
