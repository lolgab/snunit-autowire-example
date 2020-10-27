package example

class ApiImplTest extends munit.FunSuite {
  test("set and get todos") {
    val expected = Seq(TodoItem(1, "hello", false))
    ApiImpl.setTodos(expected)
    val obtained = ApiImpl.getTodos()
    assertEquals(obtained, expected)
  }
}
