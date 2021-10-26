// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/asanand/Downloads/softwares/play-samples-play-java-hello-world-tutorial/conf/routes
// @DATE:Sun Jul 07 13:20:39 IST 2019

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:7
  HomeController_1: controllers.HomeController,
  // @LINE:23
  Assets_0: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    HomeController_1: controllers.HomeController,
    // @LINE:23
    Assets_0: controllers.Assets
  ) = this(errorHandler, HomeController_1, Assets_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_1, Assets_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """SET/""" + "$" + """key<[^/]+>/""" + "$" + """value<[^/]+>""", """controllers.HomeController.setKey(key:String, value:Int)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """GET/""" + "$" + """key<[^/]+>""", """controllers.HomeController.get(key:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """SIZE""", """controllers.HomeController.getCacheSize()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """INCR/""" + "$" + """key<[^/]+>""", """controllers.HomeController.increment(key:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """KEYS/""" + "$" + """pattern<[^/]+>""", """controllers.HomeController.getAllMatching(pattern:String)"""),
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """explore""", """controllers.HomeController.explore"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tutorial""", """controllers.HomeController.tutorial"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:7
  private[this] lazy val controllers_HomeController_setKey0_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("SET/"), DynamicPart("key", """[^/]+""",true), StaticPart("/"), DynamicPart("value", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_setKey0_invoker = createInvoker(
    HomeController_1.setKey(fakeValue[String], fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "setKey",
      Seq(classOf[String], classOf[Int]),
      "POST",
      this.prefix + """SET/""" + "$" + """key<[^/]+>/""" + "$" + """value<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_HomeController_get1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("GET/"), DynamicPart("key", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_get1_invoker = createInvoker(
    HomeController_1.get(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "get",
      Seq(classOf[String]),
      "GET",
      this.prefix + """GET/""" + "$" + """key<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_HomeController_getCacheSize2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("SIZE")))
  )
  private[this] lazy val controllers_HomeController_getCacheSize2_invoker = createInvoker(
    HomeController_1.getCacheSize(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getCacheSize",
      Nil,
      "GET",
      this.prefix + """SIZE""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_HomeController_increment3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("INCR/"), DynamicPart("key", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_increment3_invoker = createInvoker(
    HomeController_1.increment(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "increment",
      Seq(classOf[String]),
      "POST",
      this.prefix + """INCR/""" + "$" + """key<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private[this] lazy val controllers_HomeController_getAllMatching4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("KEYS/"), DynamicPart("pattern", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_getAllMatching4_invoker = createInvoker(
    HomeController_1.getAllMatching(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getAllMatching",
      Seq(classOf[String]),
      "GET",
      this.prefix + """KEYS/""" + "$" + """pattern<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_HomeController_index5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index5_invoker = createInvoker(
    HomeController_1.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private[this] lazy val controllers_HomeController_explore6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("explore")))
  )
  private[this] lazy val controllers_HomeController_explore6_invoker = createInvoker(
    HomeController_1.explore,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "explore",
      Nil,
      "GET",
      this.prefix + """explore""",
      """""",
      Seq()
    )
  )

  // @LINE:19
  private[this] lazy val controllers_HomeController_tutorial7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tutorial")))
  )
  private[this] lazy val controllers_HomeController_tutorial7_invoker = createInvoker(
    HomeController_1.tutorial,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "tutorial",
      Nil,
      "GET",
      this.prefix + """tutorial""",
      """""",
      Seq()
    )
  )

  // @LINE:23
  private[this] lazy val controllers_Assets_versioned8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned8_invoker = createInvoker(
    Assets_0.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_HomeController_setKey0_route(params@_) =>
      call(params.fromPath[String]("key", None), params.fromPath[Int]("value", None)) { (key, value) =>
        controllers_HomeController_setKey0_invoker.call(HomeController_1.setKey(key, value))
      }
  
    // @LINE:9
    case controllers_HomeController_get1_route(params@_) =>
      call(params.fromPath[String]("key", None)) { (key) =>
        controllers_HomeController_get1_invoker.call(HomeController_1.get(key))
      }
  
    // @LINE:11
    case controllers_HomeController_getCacheSize2_route(params@_) =>
      call { 
        controllers_HomeController_getCacheSize2_invoker.call(HomeController_1.getCacheSize())
      }
  
    // @LINE:13
    case controllers_HomeController_increment3_route(params@_) =>
      call(params.fromPath[String]("key", None)) { (key) =>
        controllers_HomeController_increment3_invoker.call(HomeController_1.increment(key))
      }
  
    // @LINE:15
    case controllers_HomeController_getAllMatching4_route(params@_) =>
      call(params.fromPath[String]("pattern", None)) { (pattern) =>
        controllers_HomeController_getAllMatching4_invoker.call(HomeController_1.getAllMatching(pattern))
      }
  
    // @LINE:17
    case controllers_HomeController_index5_route(params@_) =>
      call { 
        controllers_HomeController_index5_invoker.call(HomeController_1.index)
      }
  
    // @LINE:18
    case controllers_HomeController_explore6_route(params@_) =>
      call { 
        controllers_HomeController_explore6_invoker.call(HomeController_1.explore)
      }
  
    // @LINE:19
    case controllers_HomeController_tutorial7_route(params@_) =>
      call { 
        controllers_HomeController_tutorial7_invoker.call(HomeController_1.tutorial)
      }
  
    // @LINE:23
    case controllers_Assets_versioned8_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned8_invoker.call(Assets_0.versioned(path, file))
      }
  }
}
