package exceptions

class StatTest extends munit.FunSuite {
  private val statName = "testStat"
  private val testRange = 1 to 10
  private val statInRange = Require.Stat(5, statName)
  private val statOutOfRange = Require.Stat(11, statName)
  private val threshold = 5
  private val valueBelowThreshold = 4
  private val statBelowThreshold = Require.Stat(valueBelowThreshold, statName)
  private val valueAboveThreshold = 6
  private val statAboveThreshold = Require.Stat(valueAboveThreshold, statName)

  test(
    "An `in` requirement should assign the correct value if it's inside the range"
  ) {
    assertEquals(statInRange in testRange, 5)
  }

  test(
    "An `in` requirement should throw an exception if it's outside the range"
  ) {
    interceptMessage[InvalidStatException](
      s"An invalid stat was found -- ${statOutOfRange.name} should be in $testRange but was ${statOutOfRange.value}"
    ) {
      statOutOfRange in testRange
    }
  }

  test(
    "An `atLeast` requirement should assign the correct value if it's greater than or equal to the threshold"
  ) {
    assertEquals(statAboveThreshold atLeast threshold, valueAboveThreshold)
  }

  test(
    "An `atLeast` requirement should throw an exception if it's less than the threshold"
  ) {
    interceptMessage[InvalidStatException](
      s"An invalid stat was found -- ${statBelowThreshold.name} should be at least $threshold but was ${statBelowThreshold.value}"
    ) {
      statBelowThreshold atLeast threshold
    }
  }

  // Had to do some investigation, but finally I can get coverage up to 100
  // I currently have no idea of what this does or how it works, but it does
  // Update: I'm a genius, I got it all, will try in future assignments
  test(
    "Other init ways"
  ) {
    /*Require.Stat.tupled((5,"Alex"))*/           // This fixes everything,
                                                  // another way of initializing
//    val init = Require.Stat.tupled((5,"Alex"))
//    Require.Stat.unapply(init)
    val varCond = Require.Stat.tupled((5,"Alex"))
    val AlexAge = varCond in (1 to 5)
    assertEquals(AlexAge,5,"It doesn't seem to behave as expected")
    interceptMessage[InvalidStatException](
      "An invalid stat was found -- Alex should be at least 21 but was 5"
    ) {
      varCond atLeast 21
    }
  }
}