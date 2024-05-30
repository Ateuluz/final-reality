package modeltest.teamstest.partytest

import exceptions.InvalidHandleException
import model.entities.characters.paladin.Paladin
import model.teams.party.Party

class PartyTest extends munit.FunSuite {
  var team1: Party = _
  var ch1: Paladin = _
  var ch2: Paladin = _
  var ch3: Paladin = _
  var ch4: Paladin = _
  override def beforeEach(context: BeforeEach): Unit = {
    ch1 = new Paladin("A", 1, 1, 1)
    ch2 = new Paladin("B", 1, 1, 1)
    ch3 = new Paladin("C", 1, 1, 1)
    ch4 = new Paladin("X", 3, 3, 3)
  }

  test("Cannot Surpass Limit of Members") {
    interceptMessage[InvalidHandleException](
      "An invalid user action was found -- Cannot add member, maximum amount reached."
    ) {
      team1 = new Party(ch1, ch2, ch3)
      team1.addMember(ch4)
    }
  }
}