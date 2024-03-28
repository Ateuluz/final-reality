package elementsTest

import elements.Sword

class SwordTest extends munit.FunSuite() {
  var tstSbjt1: Sword = _
  var tstSbjt2: Sword = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstSbjt1 = new Sword("Excal", 5, 10)
    tstSbjt2 = new Sword("Claym", 20, 30)
  }

  test("???") {
    val expected = ???
  }
}