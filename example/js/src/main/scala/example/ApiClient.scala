package example

import autowire._
import upickle.default._
import org.scalajs.dom
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object ApiClient extends autowire.Client[String, Reader, Writer] {
  override def doCall(req: Request): Future[String] = {
    dom.ext.Ajax
      .post(
        url = req.path.mkString("/"),
        data = ujson.write(req.args.mapValues(ujson.read(_)))
      )
      .map(r => r.responseText)
  }

  def read[T: Reader](p: String) = upickle.default.read[T](p)
  def write[T: Writer](r: T) = upickle.default.write(r)
}
