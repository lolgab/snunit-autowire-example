package example

import snunit._
import snunit.Autowire._

import UpickleSerializers._

import scala.concurrent.ExecutionContext.Implicits.global

object Main {
  def main(args: Array[String]): Unit = {
    AsyncServerBuilder()
      .withAutowireRouter(UpickleAutowireServer.route[Api](ApiImpl))
      .withRequestHandler(_.send(StatusCode.NotFound, "Not found", Seq.empty))
      .build()
  }
}
