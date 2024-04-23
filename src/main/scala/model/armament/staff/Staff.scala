package model.armament.staff

import model.armament.AMagicalWeapon
import model.entities.characters.{ICharacter, IStaffUser}

/**
 *
 * @param name        The name of the Sword
 * @param attack      The attack value
 * @param weight      The Sword's weight
 * @param magicAttack The magic attack value
 */
class Staff (
              name: String,
              attack: Int,
              weight: Int,
              magicAttack: Int,
            ) extends AMagicalWeapon(name,attack,weight,magicAttack) {
  override def setOwner(owner: ICharacter): Unit = {
    owner match {
      case staffUser: IStaffUser =>
        super.setOwner(staffUser)
      case _ =>
        println("Cannot set owner. Entity is not a StaffUser.")
    }
  }
}
