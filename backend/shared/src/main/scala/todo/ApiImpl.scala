package todo

import snunit.tapir.SNUnitInterpreter._
import sttp.tapir.server.ServerEndpoint

class ApiImpl(db: Db) {
  val getTodos =
    Api.getTodos.serverLogicSuccess[Id] { _ =>
      scribe.info(s"Getting todos.")
      val data = db.getTodos()
      scribe.info(s"Got todos: $data")
      Todos(data)
    }

  val putTodos =
    Api.putTodos.serverLogicSuccess[Id] { todos =>
      scribe.info(s"Setting todos: ${todos.todos}")
      db.setTodos(todos.todos)
      "Todos updated successfully"
    }

  val endpoints = List(getTodos, putTodos)
}
