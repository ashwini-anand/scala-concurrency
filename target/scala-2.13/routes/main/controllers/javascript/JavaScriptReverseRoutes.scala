// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/asanand/Downloads/softwares/play-samples-play-java-hello-world-tutorial/conf/routes
// @DATE:Sun Jul 07 13:20:39 IST 2019

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:7
package controllers.javascript {

  // @LINE:7
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def getAllMatching: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getAllMatching",
      """
        function(pattern0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "KEYS/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("pattern", pattern0))})
        }
      """
    )
  
    // @LINE:13
    def increment: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.increment",
      """
        function(key0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "INCR/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("key", key0))})
        }
      """
    )
  
    // @LINE:11
    def getCacheSize: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getCacheSize",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "SIZE"})
        }
      """
    )
  
    // @LINE:7
    def setKey: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.setKey",
      """
        function(key0,value1) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "SET/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("key", key0)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("value", value1))})
        }
      """
    )
  
    // @LINE:19
    def tutorial: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.tutorial",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tutorial"})
        }
      """
    )
  
    // @LINE:18
    def explore: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.explore",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "explore"})
        }
      """
    )
  
    // @LINE:17
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:9
    def get: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.get",
      """
        function(key0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "GET/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("key", key0))})
        }
      """
    )
  
  }

  // @LINE:23
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:23
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
