package elementsTest

import elements.Enemy

class EnemyTest extends munit.FunSuite {
  var tstSbjt1: Enemy = _
  var tstSbjt2: Enemy = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstSbjt1 = new Enemy("Mike", 100, 10, 20, 50)
    tstSbjt2 = new Enemy("Ross", 120, 15, 15, 60)
  }

  test("???") {
    val expected = ???
  }
}
