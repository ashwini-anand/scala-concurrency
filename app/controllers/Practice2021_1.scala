package controllers

import akka.actor.{Actor, ActorSystem, Props}

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Practice2021_1 extends App {

  //  val l1 = List(1,2,3)
  //  val l2 = l1:+4
  //  println(l2)
  //  val l3 = 5::l2
  //  println(l3)
  //  val l4 = 6+:l3
  //  println(l4)
  //  val l5 = List(7):::l4
  //  println(l5)

  //  case class StudentId(id: String)
  //  case class StaffId(id: String)
  //
  //  trait Printer[A]{
  //    def getString(obj: A): String
  //  }
  //
  //  def show[A](a: A)(implicit printer: Printer[A]) = s"id is ${printer.getString(a)}"
  //
  //  implicit val studentPrinter = new Printer[StudentId] {
  //    override def getString(studentId: StudentId): String = studentId.id
  //  }
  //
  //  implicit val staffPrinter = new Printer[StaffId] {
  //    override def getString(staffId: StaffId): String = staffId.id
  //  }
  //
  //  val studentid = StudentId("100")
  //  val staffId = StaffId("S100")
  //
  //  println(show(studentid))
  //  println(show(staffId))

  //  def printPing(): Unit ={
  //    println("ping")
  //    Thread.sleep(500)
  //    printPong()
  //  }
  //
  //  def printPong(): Unit = {
  //    println("pong")
  //    Thread.sleep(500)
  //    printPing()
  //  }

  //  printPing()

//  class PingPong {
//    var isPing = true
//  }
//
//  class PingClass(val pingPong: PingPong) {
//    def printPing(): Future[Unit] = Future{
//      while(true){
//        pingPong.synchronized {
//          while (!pingPong.isPing) {
//            pingPong.wait()
//          }
//          println("ping")
//          pingPong.isPing = false
//          pingPong.notifyAll()
//        }
//        Thread.sleep(500)
//      }
//    }
//  }
//
//  class PongClass(val pingPong: PingPong){
//    def printPong(): Future[Unit] = Future{
//      while(true){
//        pingPong.synchronized{
//          while(pingPong.isPing){
//            pingPong.wait()
//          }
//          println("pong")
//          pingPong.isPing = true
//          pingPong.notifyAll()
//        }
//        Thread.sleep(500)
//      }
//    }
//  }
//
//  def startPingPong(): Unit ={
//    val pingPong = new PingPong()
//    val pingClass = new PingClass(pingPong)
//    val pongClass = new PongClass(pingPong)
//    val fseq = List(pongClass.printPong(), pingClass.printPing())
//    val res = Future.sequence(fseq)
//    Await.ready(res, Duration.Inf)
//  }
//  startPingPong()

  case class PingMessage()
  case class PongMessage()

  class PingPongManager extends Actor{
    var isPing = true
    override def receive: Receive = {
      case PingMessage() =>{
        if(isPing) {
          println("ping")
          isPing = false
        }
      }
      case PongMessage() =>{
        if(!isPing) {
          println("pong")
          isPing = true
        }
      }
    }
  }

  val system = ActorSystem("PingPongManager")
  val actor = system.actorOf(Props[PingPongManager], "PingPongManager")

  class Pong{
    def printPong(): Future[Unit] = Future{
      while(true){
        actor ! PongMessage()
        Thread.sleep(500)
      }
    }
  }

  class Ping{
    def printPing(): Future[Unit] = Future{
      while(true){
        actor ! PingMessage()
        Thread.sleep(500)
      }
    }
  }

  def startPingPong(): Unit ={
    val f1 = { new Pong().printPong()}
    val f2 = { new Ping().printPing() }
    Future.sequence(List(f1,f2))
  }
  startPingPong()
}
