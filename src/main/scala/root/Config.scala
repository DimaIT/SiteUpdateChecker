package root

/**
 * @author D.Tolpekin
 */
class Config(waitTime: Int, targets: List[Monitor]) {

  def build = {
    targets.map(_.rebuild(waitTime))
  }

}
