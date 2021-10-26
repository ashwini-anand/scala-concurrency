package controllers

import java.lang.reflect.Field
import java.time.{ZoneId, ZoneOffset, ZonedDateTime}

import com.cronutils.model.CronType
import com.cronutils.model.definition.CronDefinitionBuilder
import com.cronutils.model.time.ExecutionTime
import com.cronutils.parser.CronParser
import com.sun.tools.javac.code.TypeTag
import org.joda.time.{DateTime, DateTimeZone, LocalDateTime}
import org.joda.time.chrono.ISOChronology
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable
import scala.concurrent._
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.reflect.{ClassManifest, ClassTag}

object SOTest extends App {

  case class Person(name: String, surname: String) {
    lazy val initials: String = name(0) + "." + surname(0) + "."
  }

  def getCCParams(cc: AnyRef, lazyMembers: List[String] = List("initials")) = {
    val method = lazyMembers.map {
      lmember =>
        val method = cc.getClass.getDeclaredMethod(lmember)
        val value = method.invoke(cc)
        println("method " + method.toString)
        println(" " + value)
    }
    val fields = cc.getClass.getDeclaredFields.map { f =>
      f.setAccessible(true)
      f.getName -> f.get(cc)
    }.toMap

    fields
  }

  def getCCParams1(cc: AnyRef, lazyMembers: List[String] = List("initials")) = {

    import scala.reflect.runtime.universe

    lazyMembers.map { lmember =>
      val runtimeMirror = universe.runtimeMirror(getClass.getClassLoader)
      val instanceMirror = runtimeMirror.reflect(cc)
      val nameMethod = universe.typeOf[Person].member(universe.TermName(lmember)).asMethod
      val nameLazyMethod = instanceMirror.reflectMethod(nameMethod)
      nameLazyMethod()
    }

    cc.getClass.getDeclaredFields.map { f =>
      f.setAccessible(true)
      f.getName -> f.get(cc)
    }.toMap
  }

    def getCCParams2(cc: AnyRef) = {
      val clazz = cc.getClass
      clazz.getDeclaredFields.map { f =>
        Try(clazz.getMethod(f.getName)) //get the accessor method by name of field
          .toOption
          .map(m => f.getName -> m.invoke(cc))
      }.flatten.toMap
    }

  val JohnSmith = Person("John", "Smith")
  val res = getCCParams1(JohnSmith,List("initials"))
  println(res)

  //  def quartzTest() = {
  //    val expression = "0 * * ? * * *" // every minute (the top of the minute)
  //    val currentTime = "2019-12-31T00:00:00.000Z" // get current time
  //    val expectedNextExecution = "2019-12-31T00:01:00.000Z" // get current time
  //    val cron = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ)).parse(expression)
  //    val executor = ExecutionTime.forCron(cron)
  //    val actualNextExecution = executor.nextExecution(DateTime.parse(currentTime)).toString() // call nextExecution which should give us the next top of the minute
  //    // check if nextExecution has correct next execution time
  //    val cuTime = new DateTime(currentTime)
  //    //val expectedNext = DateTime.parse(expectedNextExecution).toLocalDateTime.toDateTime.toString()
  //    val expectedNext = new DateTime(expectedNextExecution).toDateTime.toString()
  //    println("datetime cuurent time"+cuTime.toString)
  //    println("actualNextExecution "+actualNextExecution.toString)
  //    println("expectedNextExecution "+ expectedNext)
  //    assert(actualNextExecution == expectedNext, "Wrong execution time")
  //  }

  //  def quartzTest1() = {
  //    val expression = "0 * * ? * * *" // every minute (the top of the minute)
  //    val currentTime = DateTime.parse("2019-12-31T00:00:00.000Z").toLocalDateTime.toDateTime // get current time
  //    val expectedNextExecution: LocalDateTime = DateTime.parse("2019-12-31T00:01:00.000Z").toLocalDateTime // get current time
  //    //val expectedNextExecution = "2019-12-31T00:01:00.000Z"
  //    val cron = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ)).parse(expression)
  //    val executor = ExecutionTime.forCron(cron)
  //    val actualNextExecution = executor.nextExecution(currentTime).toString // call nextExecution which should give us the next top of the minute
  //    // check if nextExecution has correct next execution time
  //    println("actualNextExecution "+actualNextExecution.toString)
  //    println("expectedNextExecution "+ expectedNextExecution)
  //    assert(actualNextExecution == expectedNextExecution, "Wrong execution time")
  //  }

  //  def quartzTest2() = {
  //    val expression = "0 * * ? * * *" // every minute (the top of the minute)
  //    val currentTime = DateTime.now() // get current time
  //    println("datetime cuurent time"+currentTime.toString)
  //    val expectedNextExecution = currentTime.plusMinutes(1 ).toString // get current time
  //    val cron = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ)).parse(expression)
  //    val executor = ExecutionTime.forCron(cron)
  //    val actualNextExecution = executor.nextExecution(currentTime).toString // call nextExecution which should give us the next top of the minute
  //    // check if nextExecution has correct next execution time
  //    println("actualNextExecution "+actualNextExecution.toString)
  //    println("expectedNextExecution "+ expectedNextExecution)
  //    assert(actualNextExecution == expectedNextExecution, "Wrong execution time")
  //  }

  //  def quartzTest1() = {
  //    val expression = "0 * * ? * * *" // every minute (the top of the minute)
  //    val currentTime = "2019-12-31T00:00:00.000Z" // get current time
  //    val expectedNextExecution = DateTime.parse("2019-12-31T00:01:00.000Z").toLocalDateTime.toDateTime.toString() // get current time
  //    val cron = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ)).parse(expression)
  //    val executor = ExecutionTime.forCron(cron)
  //    val actualNextExecution = executor.nextExecution(ZonedDateTime.parse(currentTime)).toString // call nextExecution which should give us the next top of the minute
  //    // check if nextExecution has correct next execution time
  //    println("actualNextExecution "+actualNextExecution.toString)
  //    println("expectedNextExecution "+ expectedNextExecution)
  //    assert(actualNextExecution == expectedNextExecution, "Wrong execution time")
  //  }

  //quartzTest1()
  class Vehicle {
    var position: (Int, Int) = (0, 0)
    def moveLeft[T](meters: Int, vehicle: T = this): T = {
      position = position._1 - meters -> position._2
      this.asInstanceOf[T]
    }

    def moveForward(meters: Int): Vehicle= {
      position = position._1 -> (position._2 + meters)
      this
    }
  }

  class Helicopter extends Vehicle {
    var verticalDimension: Int = 0
    def flyIntoTheSky(meters: Int): Helicopter = {
      verticalDimension += meters
      this
    }
  }

  class XYZ extends Helicopter {
    var verticalDimension1: Int = 0
    def flyIntoTheSky1(meters: Int): Helicopter = {
      verticalDimension += meters
      this
    }
  }

  val heli = new Helicopter().moveLeft(10)
  //val xyz = new XYZ().moveLeft(12).
//  val xst: Helicopter =  heli.moveLeft[Helicopter](10)
  val veh = new Vehicle()
//  val abc = veh.moveLeft(10, "abc")
  println("heli "+heli.position)

  abstract class AbstractSuperClass {
    protected[controllers] def someFunction(num: Int): Int
    def addition(another: AbstractSuperClass, num: Int): Int
  }

  class SubclassSquare extends AbstractSuperClass {
    override protected[controllers] def someFunction(num: Int): Int = num * num
    override def addition(another: AbstractSuperClass, num: Int): Int =
      someFunction(num) + another.someFunction(num)
  }

  val ss1 = new SubclassSquare()
  val ss2 = new SubclassSquare()
  println("res: "+ss1.addition(ss2, 2))

  def isEmpty(input: String): String = {
    val nonEmptyStringPattern = "\\w+".r
    input match {
      case nonEmptyStringPattern() => s"matched $input"
      case _ => "n/a"
    }
  }

  println("test isEmpty "+isEmpty("abc"))

  def testMatchSof(input: String, pattern: String): Boolean = {
    pattern.r.matches(input)
  }

  println("testMatchSof -> "+ testMatchSof("abc", """(\w+)"""))

  val fruitList = List("apple", "orange", "banana", "apricot", "blueberry", "cherry")
  val removeSet = Set(2,3) // This set contains indexes of elements which should be removed from fruitList
  println(fruitList)
  val resultList = fruitList.zipWithIndex.filter(x => !(removeSet.contains(x._2))).map(_._1)
  println(resultList)

  val input = """{"id":1,"command":"connect"}"""
  val json: JsValue = Json.parse(input)
  println(json)
  val command = (json \ "command").validate[String].getOrElse("unknown command")
  val command1: JsValue = (json \ "command").get
  println(command)
}

object Types {
  val Str = "string"
  val IntNum1 = "int"
  val IntNum2 = "integer"
  val DoubleNum = "double"
  val LongNum = "long"
}