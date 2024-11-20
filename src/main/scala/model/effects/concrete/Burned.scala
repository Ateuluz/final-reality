package model.effects.concrete

import model.effects.ADamageEffect

/** Ateuluz
 *
 * @param dmg The damage taken before each turn
 */
class Burned(
              dmg: Int
            ) extends ADamageEffect(dmg, 3) {

}
