package todo

import upickle.default._

object UpickleSerializers {
  implicit val todoItemRW = macroRW[TodoItem]
  implicit val todosRW = macroRW[Todos]
  implicit val userRW = macroRW[User]
}
