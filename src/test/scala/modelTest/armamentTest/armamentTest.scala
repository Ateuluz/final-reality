package modelTest.armamentTest

import bow.Bow
import model.armament.axe.Axe
import model.armament.staff.Staff
import model.armament.sword.Sword
import model.armament.wand.Wand

class WeaponTest extends munit.FunSuite{
  var tstSbjt1: Sword = _
  var tstSbjt2: Staff = _

  override def beforeEach(context: BeforeEach): Unit = {
    tstSbjt1 = new Sword("Excal", 5, 10)
    tstSbjt2 = new Staff("Claym", 20, 30, 25)
  }

  test("Limited Variable Ranges") {
    var allowNegativeHP: Boolean = false
    var allowNegativeWeight: Boolean = false
    var allowNegativeMagicAttack: Boolean = false
    try {
      tstSbjt1 = new Sword("Excal", -1, 1)
      allowNegativeHP = true
    } catch {
      case _: java.lang.IllegalArgumentException => false
    }
    try {
      tstSbjt1 = new Sword("Excal", 1, -1)
      allowNegativeWeight = true
    } catch {
      case _: java.lang.IllegalArgumentException => false
    }
    try {
      tstSbjt2 = new Staff("Claym", 1, 1, -1)
      allowNegativeMagicAttack = true
    } catch {
      case _: java.lang.IllegalArgumentException => false
    }
    assertEquals(allowNegativeHP,false,"Negative HP shouldn't be allowed")
    assertEquals(allowNegativeWeight,false,"Negative or zero Weight shouldn't be allowed") // Speed related
    assertEquals(allowNegativeMagicAttack,false,"Negative MagicAttack shouldn't be allowed")
  }
}

class SwordTest extends munit.FunSuite() {
  var tstSbjt1: Sword = _
  var tstSbjt2: Sword = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstSbjt1 = new Sword("Excal", 5, 10)
    tstSbjt2 = new Sword("Claym", 20, 30)
  }

  //test("???") {
  //  val expected = ???
  //}
}

class AxeTest extends munit.FunSuite() {
  var tstSbjt1: Axe = _
  var tstSbjt2: Axe = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstSbjt1 = new Axe("Excal", 5, 10)
    tstSbjt2 = new Axe("Claym", 20, 30)
  }

  //test("???") {
  //  val expected = ???
  //}
}

class BowTest extends munit.FunSuite() {
  var tstSbjt1: Bow = _
  var tstSbjt2: Bow = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstSbjt1 = new Bow("Excal", 5, 10)
    tstSbjt2 = new Bow("Claym", 20, 30)
  }

  //test("???") {
  //  val expected = ???
  //}
}

class WandTest extends munit.FunSuite() {
  var tstSbjt1: Wand = _
  var tstSbjt2: Wand = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstSbjt1 = new Wand("Excal", 5, 10, 5)
    tstSbjt2 = new Wand("Claym", 20, 30, 10)
  }

  //test("???") {
  //  val expected = ???
  //}
}

class StaffTest extends munit.FunSuite() {
  var tstSbjt1: Staff = _
  var tstSbjt2: Staff = _

  override def beforeEach(context: BeforeEach): Unit = {
    //super.beforeEach(context)
    tstSbjt1 = new Staff("Excal", 5, 10, 5)
    tstSbjt2 = new Staff("Claym", 20, 30, 10)
  }

  //test("???") {
  //  val expected = ???
  //}
}


