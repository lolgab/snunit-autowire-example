package todo

import com.github.sqlite4s._
import java.io._

class SqliteDbTest extends munit.FunSuite {
  test("set and get todos") {
    val expected = List(TodoItem(1, "hello", false))
    // SqliteDb.setTodos(expected)
    // val obtained = SqliteDb.getTodos()
    // assertEquals(obtained, expected)
  }
}