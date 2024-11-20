package model.effects.concrete

import model.effects.ADamageEffect

/** Ateuluz
 *
 * @param dmg The damage taken before each turn
 */
class Poisoned(
                dmg: Int
              ) extends ADamageEffect(dmg, 4) {

}
