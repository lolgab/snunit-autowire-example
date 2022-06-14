package todo

import sttp.client3.HttpClientSyncBackend

object SttpBackend {
  val backend = HttpClientSyncBackend()
}
