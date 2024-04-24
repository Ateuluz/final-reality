package modelTest.entitiesTest

import model.entities.characters.paladin.Paladin

class EntityTest extends munit.FunSuite {
  var ch1: Paladin = _
  override def beforeEach(context: BeforeEach): Unit = {
    ch1 = new Paladin("A", 1, 1, 1)
  }

  test("Have name") {
    assertEquals(ch1.getName,"A")
  }

  test("Can Alter Hp") {
    ch1.setHp(2)
    assertEquals(ch1.getHp,2)
  }

}
