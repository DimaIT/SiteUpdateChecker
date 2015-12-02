package root

import scala.io.StdIn


/**
 * @author D.Tolpekin
 */
object Main {
  val globalConfig = Reader.read()

  def main(args: Array[String]): Unit = {

    val scanners = globalConfig.build.map(m => new Thread(m))
    scanners.foreach(_.setDaemon(true))
    scanners.foreach(_.start())

    requestExit(StdIn.readLine())
    println("Bye-bye")
  }

  def requestExit(str: String) {
    if (str != "quit" && str != "exit") requestExit(StdIn.readLine())
  }
}

