import final_reality.Main
import scala.concurrent.duration._
import scala.concurrent.{Await, Future, TimeoutException}
import scala.concurrent.ExecutionContext.Implicits.global

class MainTest extends munit.FunSuite {

  def withTimeLimit[T](timeLimit: FiniteDuration)(block: => T): T = {
    val futureResult = Future(block)
    Await.result(futureResult, timeLimit)
  }

  test("Init Main") {
    intercept[TimeoutException]{
      val main = Main
      withTimeLimit(1.second) {main.main(Array[String]())}
    }
  }
}
