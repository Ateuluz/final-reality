package elementsTest

import elements.Paladin
import elements.Warrior
import elements.Ninja
import elements.WhiteMage
import elements.DarkMage
import elements.Weapon

//import org.junit.*
//import org.junit.Assert.*

class PaladinTest extends munit.FunSuite{
  var tstSbjt1: Paladin = _
  var tstSbjt2: Paladin = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstSbjt1 = new Paladin("John", 80, 20, 5)
    tstSbjt2 = new Paladin("Doe", 200, 10, 25)
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