package todo

class InMemoryDb extends Db {
  private var data: List[TodoItem] = List[TodoItem]() 
  def getTodos(): List[TodoItem] = data

  def setTodos(v: List[TodoItem]): Unit = data = v
}
