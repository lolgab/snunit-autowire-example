package todo

import com.raquo.laminar.api.L._
import org.scalajs.dom.document

object Main {
  def main(args: Array[String]): Unit = {
    render(document.getElementById("app-container"), TodoMvcApp())
  }
}
