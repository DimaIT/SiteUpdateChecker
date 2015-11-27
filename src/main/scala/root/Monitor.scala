package root

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import org.jsoup.Jsoup

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
    try
      scan()
    catch {
      case e: Exception =>
        println("Error occurs while scanning " + label + "; " + "trying to restart in 10 mins")
        Thread.sleep(1000 * 60 * 10)
    }
    recursiveScanning()
  }

  def scan() = {
    var previous: String = get()
    var current: String = get()
    while (current == previous) {
      Thread.sleep(interval)
      previous = current
      current = get()
    }
    println(now() + " >> " + label + " changed")
  }

  def get() = {
    if (!selector.isEmpty)
      Jsoup.connect(url).get.select(selector).html()
    else
      Jsoup.connect(url).get.html()
  }

  def now() = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yy hh:mm"))

  def f(s:String) = if (s.length > 25) s.substring(0, 20) + "..." else s
  override def toString: String = label + ":\turl=" + f(url) + ", selector=" + f(selector) + ", interval=" + (interval/60/1000)
}
