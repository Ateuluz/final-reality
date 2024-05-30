package modeltest.teamstest

import model.entities.playablecharacters.paladin.Paladin
import model.teams.party.Party
import exceptions.{InvalidHandleException, InvalidStatException}
import model.armament.sword.Sword
import model.entities.IEntity
import model.entities.enemies.enemy.Enemy
import model.entities.playablecharacters.ICharacter

import scala.collection.mutable.ArrayBuffer

class TeamTest extends munit.FunSuite {
  var team1: Party = _
  var ch1: Paladin = _
  var ch2: Paladin = _
  var ch3: Paladin = _
  var ch4: Paladin = _
  var wp1: Sword   = _
  var en1: Enemy   = _
  override def beforeEach(context: BeforeEach): Unit = {
    ch1 = new Paladin("A", 1, 1, 1)
    ch2 = new Paladin("B", 1, 1, 1)
    ch3 = new Paladin("C", 1, 1, 1)
    ch4 = new Paladin("X", 3, 3, 3)
    wp1 = new Sword("D", 3, 3)
    en1 = new Enemy("Y", 11, 10, 100, 10)
    ch4.equip(wp1)
  }

  test("Cannot Init w/o Enough Members") {
    interceptMessage[InvalidStatException](
      s"An invalid stat was found -- initialMembers should be in ${3 to 3} but was ${2}"
    ) {
      team1 = new Party(ch1, ch2)
    }
  }

  test("Teams Have Members") {
    team1 = new Party(ch1, ch2, ch3)
    assertEquals(team1.getMembers,ArrayBuffer[ICharacter](ch1,ch2,ch3))
  }

  test("Can be Defeated") {
    team1 = new Party(ch1, ch2, ch3)
    assertEquals(team1.isDefeated, false)
    en1.attack(ch1)
    en1.attack(ch2)
    en1.attack(ch3)
    assertEquals(team1.isDefeated, true)
  }

  test("Can Replace Member") {
    team1 = new Party(ch1, ch2, ch3)
    team1.changeMember(ch1, Some(ch4))
    assertEquals(team1.getMembers, ArrayBuffer[ICharacter](ch2,ch3,ch4))
  }

  test("Limit Removal") {
    interceptMessage[InvalidHandleException](
      "An invalid user action was found -- Cannot remove member, minimum amount reached."
    ) {
      team1 = new Party(ch1, ch2, ch3)
      team1.changeMember(ch1, None)
    }
  }
}
