package todo

import sttp.client3.CurlBackend
import sttp.client3

object SttpBackend {
  val backend = CurlBackend()
}
