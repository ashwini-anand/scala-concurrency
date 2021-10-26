// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/asanand/Downloads/softwares/play-samples-play-java-hello-world-tutorial/conf/routes
// @DATE:Sun Jul 07 13:20:39 IST 2019

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:7
package controllers {

  // @LINE:7
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def getAllMatching(pattern:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "KEYS/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("pattern", pattern)))
    }
  
    // @LINE:13
    def increment(key:String): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "INCR/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("key", key)))
    }
  
    // @LINE:11
    def getCacheSize(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "SIZE")
    }
  
    // @LINE:7
    def setKey(key:String, value:Int): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "SET/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("key", key)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("value", value)))
    }
  
    // @LINE:19
    def tutorial(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "tutorial")
    }
  
    // @LINE:18
    def explore(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "explore")
    }
  
    // @LINE:17
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
    // @LINE:9
    def get(key:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "GET/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("key", key)))
    }
  
  }

  // @LINE:23
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:23
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
