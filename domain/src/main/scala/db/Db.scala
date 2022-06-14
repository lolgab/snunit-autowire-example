package todo

trait Db {
  def getTodos(): List[TodoItem]

  def setTodos(v: List[TodoItem]): Unit
}
