package controller.states

import controller.IGameController
import exceptions.{InvalidActionException, InvalidHolderException}
import model.armament.concrete.{Sword, Wand}
import model.entities.enemies.Enemy
import model.entities.playablecharacters.ICharacter
import model.teams.concrete.Enemies

/** Ateuluz
 *
 * @param controller The associated controller for the state
 */
class StartPhase(
                  controller: IGameController
                ) extends AGameState(controller) {

  /** Ateuluz
   *
   * A method that will assign a starting weapon for a character
   */
  private def mockEquip(char: ICharacter): Unit = {
    println(s"Equipping basic weapon to ${char.getName}")
    try {
      char.equip(new Sword("Mock Sword", 10, 10))
    } catch {
      case _: InvalidActionException =>
        char.equip(new Wand("Mock Wand", 10, 10, 10))
      case _: InvalidHolderException =>
        char.equip(new Wand("Mock Wand", 10, 10, 10))
    }
  }

  /** Ateuluz
   *
   * Equip a weapon on a character
   */
  private def equip(curChar: Int): Unit = {
    val weapons = controller.weaponInventory
    // Notation abuse, but functionality is the same
    val lst = weapons.zipWithIndex.map { case (weapon, index) =>
      s"${index + 1}: ${weapon.getName}"
    }.mkString("\n")
    val prompt = "Choose new character:\n" + lst
    val opt = controller.getInput(prompt)

    try {
      val index = opt.toInt - 1
      if (index < 0 || index >= weapons.length)
        println("Invalid choice. Please choose a valid character number.")
      else {
        val weapon = weapons(index)
        val character = controller.turnScheduler.party.get.getMembers(curChar)
        if (weapon.getOwner.isDefined)
          if (weapon.getOwner.get != character)
            println("Invalid choice. Weapon already assigned.")
          else
            println(s"${weapon.getName} | ${weapon.getOwner.get.getName}")
        else {
          character.equip(weapon)
        }
      }
    } catch {
      case _: NumberFormatException => println("Invalid input. Please enter a number.")
      case _: InvalidHolderException => println("Invalid assignation. Please try another number.")
    }
  }

  /** Ateuluz
   *
   * Change a character to one of the default ones
   */
  private def change(curChar: Int): Unit = {
    val characters = controller.characterInventory
    val prompt = "Choose new character:\n" + mapEntitiesToIndexedString(characters)
    val opt = controller.getInput(prompt)

    try {
      val index = opt.toInt - 1
      if (index >= 0 && index < characters.length) {
        val toAdd: ICharacter = characters(index)
        if (controller.turnScheduler.party.get.getMembers.contains(toAdd))
          println("Invalid choice. Character in team already.")
        else {
          val oldChar = controller.turnScheduler.party.get.getMembers(curChar)
          println(s"Changing ${oldChar.getName} for ${toAdd.getName}")
          oldChar.unEquip()
          mockEquip(toAdd)
          controller.turnScheduler.party.get.changeMember(oldChar, Some(toAdd))
          controller.turnScheduler.forceMembersUpdate()
        }
      } else
        println("Invalid choice. Please choose a valid character number.")
    } catch {
      case _: NumberFormatException => println("Invalid input. Please enter a number.")
    }
  }

  /** Ateuluz
   *
   * Delegate difficulty changing
   */
  private def handleDiffChange(): Unit = {
    val opt = controller.getInput(
      "Choose from the following: \n1 \n2 "
    )
    if (opt == "1") {
      controller.turnScheduler.unbindEnemies()
      controller.turnScheduler.enemyTeam = new Enemies(
        new Enemy("Lower Demon 1", 50, 5, 15, 30),
        new Enemy("Lower Demon 2", 60, 4, 18, 40)
      )
    } else if (opt == "2") {
      controller.turnScheduler.unbindEnemies()
      controller.turnScheduler.enemyTeam = new Enemies(
        new Enemy("Lower Demon 1",  50,  5,  15,  30),
        new Enemy("Lower Demon 2",  60,  4,  18,  40),
        new Enemy("High Demon 1",  120, 10,  35, 120),
        new Enemy("High Demon 2",  100, 12,  40, 140),
        new Enemy("Demon King",    999, 15, 100, 300)
      )
    } else println("> Invalid option")
  }

  override def step(): Unit = {
    val CH = controller.turnScheduler.party.get.getMembers
    var opt = controller.getInput(
      s"Choose from the following: \n0: Begin \n1: Set Character 1 [${CH(1-1).getName}] \n2: Set Character 2 [${CH(2-1).getName}] \n3: Set Character 3 [${CH(3-1).getName}] \n4: Change Difficulty "
    )
    if (opt == "0")
      controller.state = GameStateFactory.createState("Transition", controller)
    else if (opt == "4") {
      handleDiffChange()
    } else {

      def logic(n: Int): Unit = {
        opt = controller.getInput(
          "Choose from the following: \n1: Equip \n2: Change "
        )
        if      (opt == "1") equip (n)
        else if (opt == "2") change(n)
      }

      if (opt == "1" || opt == "2" || opt == "3") logic(opt.toInt - 1)
    }
    controller.step()
  }
}
