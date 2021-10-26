package controllers

import akka.actor.{Actor, ActorSystem, Props}

object SimpleActorExample extends App {
  class SimpleActor extends Actor {
    override def receive: Receive = {
      case s: String => println("String "+s)
      case i: Int => println("Int "+i)
    }
  }

  val system = ActorSystem("SimpleSystem")
  val actor = system.actorOf(Props[SimpleActor], "SimpleActor")

  println("before message")
  actor ! "Hi there"
  println("After String")
  actor ! 2
  println("after int")
  actor ! 'a'
  println("after char")
}
