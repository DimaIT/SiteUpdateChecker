package root


/**
 * @author D.Tolpekin
 */
object Main {
  val globalConfig = Reader.read()

  def main(args: Array[String]): Unit = {

    val scanners = globalConfig.build.map(m => new Thread(m))
    scanners.foreach(_.start())



    while (true) Thread.sleep(5000)
  }
}

