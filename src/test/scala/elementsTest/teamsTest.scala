package elementsTest

import elements.{Enemies, Party}

class PartyTest extends munit.FunSuite{
  var tstSbjt1: Party = _
  var tstSbjt2: Party = _

  override def beforeEach(context: BeforeEach): Unit = {
    tstSbjt1 = new Party
  }

  test("Party size") {
    val expected = 3
    assertEquals(tstSbjt1.characters.length,expected,"The number of characters allowed should be 3")
  }
}

class EnemiesTest extends munit.FunSuite{
  var tstSbjt1: Enemies = _
  var tstSbjt2: Enemies = _

  override def beforeEach(context: BeforeEach): Unit = {
    tstSbjt1 = new Enemies
  }

  test("Enemy party size") {
    val expected = true
    val actual   = tstSbjt1.characters.length <= 5
    assertEquals(actual,expected,"No more than 5 characters allowed")
  }
}