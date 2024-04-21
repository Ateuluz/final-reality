package model.teams.enemies

import model.enemies.enemy.Enemy

class Enemies (quantity: Int) {
  val characters: Array[Enemy] = Array.ofDim[Enemy](quantity)

  def addEnemy(enemy: Enemy): Unit = {
    for (slot <- 0 until this.characters.length){
      if (this.characters(slot) == null) {
        this.characters(slot) = enemy
        return
      }
    }
  }
}
