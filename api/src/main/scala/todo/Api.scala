package todo

import sttp.tapir._
import sttp.tapir.json.upickle._
import sttp.tapir.generic.auto._
import UpickleSerializers._

object Api {
  val getTodos = endpoint.get.in("todo").out(jsonBody[Todos])
  val putTodos = endpoint.put.in("todo").in(jsonBody[Todos]).out(stringBody)
}
