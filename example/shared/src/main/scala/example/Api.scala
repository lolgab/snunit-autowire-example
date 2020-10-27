package example

trait Api {
  def getTodos(): Seq[TodoItem]
  def setTodos(v: Seq[TodoItem]): Unit
}
