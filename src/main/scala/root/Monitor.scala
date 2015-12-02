package root

import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import org.jsoup.Jsoup

import Main.{globalConfig => conf}

/**
 * @author D.Tolpekin
 */
class Monitor(url: String, selector: String = "", name: String = "", intervalMin: Int = -1) extends Runnable {
  val interval = intervalMin * 1000 * 60
  val label = if (name.isEmpty) url else name

  def rebuild(newInterval: Int) = {
    if (intervalMin == -1)
      new Monitor(url, selector, name, newInterval)
    else
      this
  }

  override def run() {
    recursiveScanning()
  }

  def recursiveScanning() {
    try {
      val html: String = get()
      println("Begins to scan " + toString)
      if (conf.showFirstScan)
        println("First scan: \n" +
          (if (html.length > 210) html.substring(0,100) + "\n... MORE CONTENT ...\n" + html.substring(html.length - 100) else html)
          + '\n')
      scan(html)
    } catch {
      case e: InterruptedException => return // exits silently
      case e: Exception =>
        println("Error (" + e.getMessage + ") occurs while scanning \"" + label + "\"; " +
          "trying to restart in " + (intervalMin * 2) + " mins")
        Thread.sleep(interval * 5)
    }
    recursiveScanning()
  }

  def scan(previous: String): Unit = {
    val current: String = get()
    Thread.sleep(interval)
    if (previous != current) {
      println(now() + " >> " + label + " changed")
      Player.play()
    }
    scan(current)
  }

  def get() = {
    if (!selector.isEmpty)
      Jsoup.parse(new URL(url), conf.connectionTimeOut * 1000).select(selector).html()
    else
      Jsoup.parse(new URL(url), conf.connectionTimeOut * 1000).html()
  }

  def now() = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yy hh:mm"))

  def f(s: String, i: Int = 20) = if (s.length > i + 5) s.substring(0, i) + "..." else s

  override def toString: String = label + ":\turl=" + f(url) + ", selector=" + f(selector) + ", interval=" + (interval / 60 / 1000)
}
