package example

import upickle.default._

object UpickleSerializers {
  implicit val todoItemRW = macroRW[TodoItem]
  implicit val userRW = macroRW[User]
}
