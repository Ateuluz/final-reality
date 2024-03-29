package elementsTest

import elements.{BlackMage, Ninja, Paladin, Sword, Warrior, Weapon, WhiteMage}

class PaladinTest extends munit.FunSuite{
  var tstSbjt1: Paladin = _
  var tstSbjt2: Paladin = _
  var tstObjt1: Sword   = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstObjt1 = new Sword("Excal", 10, 20, tstSbjt2)
    tstSbjt1 = new Paladin("John", 80, 20, 5)
    tstSbjt2 = new Paladin("Doe", 200, 10, 25, tstObjt1)
  }

  test("Weapon Ownership") {
    val expected: Sword = tstObjt1
    assertEquals(tstSbjt2.weapon,expected,"Weapon should be the given")
  }

  test("Null Weapon Ownership") {
    val expected: Weapon = null
    assertEquals(tstSbjt1.weapon,expected,"Weapon should be null")
  }
}

class WarriorTest extends munit.FunSuite{
  var tstSbjt1: Warrior = _
  var tstSbjt2: Warrior = _
  var tstObjt1: Sword   = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstObjt1 = new Sword("Excal", 10, 20, tstSbjt2)
    tstSbjt1 = new Warrior("John", 80, 20, 5)
    tstSbjt2 = new Warrior("Doe", 200, 10, 25, tstObjt1)
  }

  test("Weapon Ownership") {
    val expected: Sword = tstObjt1
    assertEquals(tstSbjt2.weapon,expected,"Weapon should be the given")
  }

  test("Null Weapon Ownership") {
    val expected: Weapon = null
    assertEquals(tstSbjt1.weapon,expected,"Weapon should be null")
  }
}

class NinjaTest extends munit.FunSuite{

}

class DarkMageTest extends munit.FunSuite{

}

class WhiteMageTest extends munit.FunSuite{

}