package controllers

import java.util.concurrent.{BlockingQueue, LinkedBlockingDeque}
import java.util.concurrent.atomic.AtomicInteger

import scala.collection.mutable
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object ProdConsBlockingQueue extends App {

  class Producer(val id: Int, val queue: BlockingQueue[String]){
    def produce = Future{
      //while(true){
        (1 to 5).foreach(item => {
          val qitem = s"$id $item"
          println(s"producing $qitem")
          queue.put(qitem)
        })
      //}
    }
  }

  class Consumer(val id: Int, val queue: BlockingQueue[String]){
    def consume = Future{
      while(true){
        val qItem = queue.take()
        println(s"consumer $id consuming $qItem")
      }
    }
  }

  def multiProdCons(nCons: Int, nProd: Int) = {
    val queue = new LinkedBlockingDeque[String](5)
    val prods: Seq[Future[Unit]] = (1 to nProd).map(id => new Producer(id, queue).produce)
    val cons: Seq[Future[Unit]] = (1 to nCons).map(id => new Consumer(id, queue).consume)
    Future.sequence(prods ++ cons)
  }

  Await.ready(multiProdCons(2,2), Duration.Inf)
}
