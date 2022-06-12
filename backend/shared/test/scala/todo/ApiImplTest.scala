package todo

class ApiImplTest extends munit.FunSuite {
  val apiImpl = new ApiImpl(new InMemoryDb)

  test("set and get todos") {
    val expected = List(TodoItem(1, "hello", false))
    apiImpl.setTodos(expected)
    val obtained = apiImpl.getTodos()
    assertEquals(obtained, expected)
  }
}
