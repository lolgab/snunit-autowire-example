package todo

import snunit._
import snunit.tapir.SNUnitInterpreter._

object Main {
  def main(args: Array[String]): Unit = {
    val db: Db = new InMemoryDb
    val api = new ApiImpl(db)

    snunit.SyncServerBuilder
      .build(toHandler(api.endpoints))
      .listen()
  }
}
