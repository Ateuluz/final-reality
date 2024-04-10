package modelTest

import model.characters.{AxeBearer, BlackMage, BowBearer, Ninja, Paladin, StaffUser, SwordBearer, WandUser, Warrior, WhiteMage}
import model.armament.{Sword, Wand, Weapon}

class CharacterTest extends munit.FunSuite{
  var tstSbjt1: Paladin   = _
  var tstSbjt2: WhiteMage = _
  var tstSbjt3: Warrior   = _
  var tstObjt1: Sword     = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstObjt1 = new Sword("Excal", 10, 20, tstSbjt1)
    tstSbjt1 = new Paladin("John", 80, 20, 5, tstObjt1)
    tstSbjt2 = new WhiteMage("Doe", 200, 10, 25, 150)
  }

  test("Limited Variable Ranges") {
    var allowNegativeHP: Boolean = false
    var allowNegativeDefense: Boolean = false
    var allowNegativeWeight: Boolean = false
    var allowNegativeMana: Boolean = false
    try {
      tstSbjt3 = new Warrior("Joe", -1, 1, 1)
      allowNegativeHP = true
    } catch {
      case _: java.lang.IllegalArgumentException =>
    }
    try {
      tstSbjt3 = new Warrior("Joe", 1, -1, 1)
      allowNegativeDefense = true
    } catch {
      case _: java.lang.IllegalArgumentException =>
    }
    try {
      tstSbjt3 = new Warrior("Joe", 1, 1, -1)
      allowNegativeWeight = true
    } catch {
      case _: java.lang.IllegalArgumentException =>
    }
    try {
      tstSbjt2 = new WhiteMage("Joe", 1, 1, 1, -1)
      allowNegativeMana = true
    } catch {
      case _: java.lang.IllegalArgumentException =>
    }
    assertEquals(allowNegativeHP,false,"Negative HP shouldn't be allowed")
    assertEquals(allowNegativeDefense,false,"Negative Defense shouldn't be allowed")
    assertEquals(allowNegativeWeight,false,"Negative or zero Weight shouldn't be allowed") // Speed related
    assertEquals(allowNegativeMana,false,"Negative Mana shouldn't be allowed")
  }
}

class PaladinTest extends munit.FunSuite{
  var tstSbjt1: Paladin = _
  var tstSbjt2: Paladin = _
  var tstSbjt3: Paladin = _
  var tstObjt1: Sword   = _

  override def beforeEach(context: BeforeEach): Unit = {
    tstObjt1 = new Sword("Excal", 10, 20, tstSbjt2)
    tstSbjt1 = new Paladin("John", 80, 20, 5)
    tstSbjt2 = new Paladin("Doe", 200, 10, 25, tstObjt1)
  }

  test("Weapon User") {
    assertEquals(tstSbjt1.isInstanceOf[SwordBearer],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[AxeBearer],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[BowBearer],false,"Shouldn't be allowed")
    assertEquals(tstSbjt1.isInstanceOf[WandUser],false,"Shouldn't be allowed")
    assertEquals(tstSbjt1.isInstanceOf[StaffUser],false,"Shouldn't be allowed")
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

  test("Weapon User") {
    assertEquals(tstSbjt1.isInstanceOf[SwordBearer],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[AxeBearer],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[BowBearer],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[WandUser],false,"Shouldn't be allowed")
    assertEquals(tstSbjt1.isInstanceOf[StaffUser],false,"Shouldn't be allowed")
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
  var tstSbjt1: Ninja   = _
  var tstSbjt2: Ninja   = _
  var tstObjt1: Sword   = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstObjt1 = new Sword("Excal", 10, 20, tstSbjt2)
    tstSbjt1 = new Ninja("John", 80, 20, 5)
    tstSbjt2 = new Ninja("Doe", 200, 10, 25, tstObjt1)
  }

  test("Weapon User") {
    assertEquals(tstSbjt1.isInstanceOf[SwordBearer],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[AxeBearer],false,"Shouldn't be allowed")
    assertEquals(tstSbjt1.isInstanceOf[BowBearer],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[WandUser],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[StaffUser],false,"Shouldn't be allowed")
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

class BlackMageTest extends munit.FunSuite{
  var tstSbjt1: BlackMage = _
  var tstSbjt2: BlackMage = _
  var tstObjt1: Sword     = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstObjt1 = new Sword("Excal", 10, 20, tstSbjt2)
    tstSbjt1 = new BlackMage("John", 80, 20, 5, 100)
    tstSbjt2 = new BlackMage("Doe", 200, 10, 25, 200, tstObjt1)
  }

  test("Weapon User") {
    assertEquals(tstSbjt1.isInstanceOf[SwordBearer],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[AxeBearer],false,"Shouldn't be allowed")
    assertEquals(tstSbjt1.isInstanceOf[BowBearer],false,"Shouldn't be allowed")
    assertEquals(tstSbjt1.isInstanceOf[WandUser],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[StaffUser],true,"Should be allowed")
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

class WhiteMageTest extends munit.FunSuite{
  var tstSbjt1: WhiteMage = _
  var tstSbjt2: WhiteMage = _
  var tstObjt1: Wand      = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstObjt1 = new Wand("Excal", 10, 20, 10, tstSbjt2)
    tstSbjt1 = new WhiteMage("John", 80, 20, 5, 50)
    tstSbjt2 = new WhiteMage("Doe", 200, 10, 25, 150, tstObjt1)
  }

  test("Weapon User") {
    assertEquals(tstSbjt1.isInstanceOf[SwordBearer],false,"Shouldn't be allowed")
    assertEquals(tstSbjt1.isInstanceOf[AxeBearer],false,"Shouldn't be allowed")
    assertEquals(tstSbjt1.isInstanceOf[BowBearer],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[WandUser],true,"Should be allowed")
    assertEquals(tstSbjt1.isInstanceOf[StaffUser],true,"Should be allowed")
  }

  test("Weapon Ownership") {
    val expected: Wand = tstObjt1
    assertEquals(tstSbjt2.weapon,expected,"Weapon should be the given")
  }

  test("Null Weapon Ownership") {
    val expected: Weapon = null
    assertEquals(tstSbjt1.weapon,expected,"Weapon should be null")
  }
}