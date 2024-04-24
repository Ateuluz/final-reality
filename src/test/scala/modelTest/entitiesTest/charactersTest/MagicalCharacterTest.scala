package modelTest.entitiesTest.charactersTest

import model.entities.characters.whiteMage.WhiteMage

class MagicalCharacterTest extends munit.FunSuite {
  var ch1: WhiteMage =_
  override def beforeEach(context: BeforeEach): Unit = {
    ch1 = new WhiteMage("A", 1, 1, 1, 1)
  }

  test("Mana can be Modified") {
    ch1.setMana(2)
    assertEquals(ch1.getMana,2)
  }
}
