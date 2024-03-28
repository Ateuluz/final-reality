package elementsTest

import elements.Paladin
import elements.Warrior
import elements.Ninja
import elements.WhiteMage
import elements.DarkMage

//import org.junit.*
//import org.junit.Assert.*

class PaladinTest extends munit.FunSuite{
  var tstSbjt1: Paladin = _
  var tstSbjt2: Paladin = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstSbjt1 = new Paladin()
    tstSbjt2 = new Paladin()
  }

  test("???") {
    val expected = ???
  }
}

class WarriorTest extends munit.FunSuite{

}

class NinjaTest extends munit.FunSuite{

}

class DarkMageTest extends munit.FunSuite{

}

class WhiteMageTest extends munit.FunSuite{

}