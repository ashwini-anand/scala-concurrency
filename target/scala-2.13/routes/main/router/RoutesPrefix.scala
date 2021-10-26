// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/asanand/Downloads/softwares/play-samples-play-java-hello-world-tutorial/conf/routes
// @DATE:Sun Jul 07 13:20:39 IST 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
