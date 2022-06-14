package todo

case class Todos(todos: List[TodoItem])

case class TodoItem(id: Int, text: String, completed: Boolean)

case class User(username: String, password: String)
