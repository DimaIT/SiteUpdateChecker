package root

/**
 * @author D.Tolpekin
 */
class Config(waitTime: Int = 20, val showFirstScan: Boolean = true, val connectionTimeOut: Int = 5, targets: List[Monitor]) {
  def build = {
    targets.map(_.rebuild(waitTime))
  }
}
