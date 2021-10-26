package controllers

import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{Actor, ActorSystem, Props}

import scala.collection.mutable
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import akka.pattern.ask
import akka.util.Timeout
import scala.language.postfixOps
import akka.pattern.pipe

import concurrent.duration._

object ProdConsActor extends App {

  case class Produce(msg: String)

  case class Consume()

  class QueueManagerActor extends Actor {
    val queue = new mutable.Queue[String]

    override def receive: Receive = {
      case Produce(msg) => {
        println(s"enqueuing item $msg")
        queue.enqueue(msg)
      }

      case Consume() => {
        //there may be problem with below line, what will happen if queue is empty: NoSuchElementException
        val item = queue.last
        println(s"in actor consuming item $item")
        Future.successful(queue.dequeue()).pipeTo(sender)
      }
    }
  }

  val system = ActorSystem("QueueManagerSystem")
  val actor = system.actorOf(Props[QueueManagerActor], "QueueManagerActor")

  class Producer(id: Int, qItem: AtomicInteger) {
    def produce = Future {
      while (true) {
        val newItem = qItem.incrementAndGet()
        val msg = s"$id $newItem"
        actor ! Produce(msg)
      }
    }
  }

  class Consumer(id: Int){
    implicit val timeOut: Timeout = 30 seconds
    def consume = Future{
      while(true){
        val res = (actor ? Consume()).mapTo[String]
        res.map{ r=>
          println(s"printing message in cons $r")
        }
      }
    }
  }

  def multiProdCons(nCons: Int, nProd: Int) = {
    val qItem = new AtomicInteger(0)
    val prods: Seq[Future[Unit]] = (1 to nProd).map(id => new Producer(id, qItem).produce)
    val cons: Seq[Future[Unit]] = (1 to nCons).map(id => new Consumer(id).consume)
    Future.sequence(prods ++ cons)
  }

  multiProdCons(1,1)
}
