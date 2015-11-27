package root

import java.util.concurrent.ForkJoinPool

/**
 * @author D.Tolpekin
 */
object Main {
  def main(args: Array[String]): Unit = {

    Reader.read().foreach(m => {
      println(m)
      new Thread(m).start()
    })
    while (true) Thread.sleep(5000)
  }
}

