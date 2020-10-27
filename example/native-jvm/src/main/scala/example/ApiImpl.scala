package example

object ApiImpl extends Api {

  /**
    * This is obviously wrong, because different
    * instances of the app will have different values.
    * Todos are shared between different users.
    * It is probably fine for the purpose of this
    * example application.
    */
  private var data = Seq.empty[TodoItem]

  def getTodos(): Seq[TodoItem] = data
  def setTodos(v: Seq[TodoItem]): Unit = data = v
}
