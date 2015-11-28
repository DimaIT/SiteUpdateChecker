package root

import org.json4s._
import org.json4s.native.JsonMethods._

import scala.io.Source

/**
 * @author D.Tolpekin
 */
object Reader {
  def read() = {
    implicit val formats = DefaultFormats
    parse(Source.fromFile("config.json").mkString).extract[Config]
  }
}
