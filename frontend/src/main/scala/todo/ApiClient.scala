package todo

import com.raquo.laminar.api.L._
import org.scalajs.dom.ext.KeyCode
import UpickleSerializers._
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import sttp.client3._
import sttp.tapir.client.sttp.SttpClientInterpreter
import sttp.tapir._
import scala.concurrent.Future

object ApiClient {
  private val interpreter = SttpClientInterpreter()
  private val backend = FetchBackend()
  def getTodos(): Future[Todos] = {
    interpreter
      .toClientThrowErrors(Api.getTodos, None, backend)
      .apply()
  }
  def putTodos(todos: Todos) = {
    interpreter
      .toClientThrowErrors(Api.putTodos, None, backend)
      .apply(todos)
  }
}
