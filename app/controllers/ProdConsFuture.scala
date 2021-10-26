package controllers

import java.util.concurrent.atomic.AtomicInteger

import scala.collection.mutable
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.Random

object ProdConsFuture extends App {

  /** Test Future completion **/
  //  def futTest = {
  //    println("in futTest")
  //    val series: Seq[Int] = 1 to 10000000
  //    val twoTimes = series.map{ num =>
  //      num*2
  //    }
  //    twoTimes.map{ num =>
  //      if(num==1000000) {
  //        println(s"printing $num")
  //      }
  //    }
  //  }
  //  Future(futTest).map(_ => println("future completed"))
  //  val outSeries = 1 to 1000
  //  outSeries.map(x => if(x ==500) println(x))
  //  println("Future test towards end line")
  //  //Await.ready(futTest, Duration.Inf)
  //  Thread.sleep(5000)
  //Test Future completion ends


  /** Test Future 2 completion **/
  //  def futTest2 = Future{
  //    println("in futTest")
  //    val series: Seq[Int] = 1 to 10000000
  //    val twoTimes = series.map{ num =>
  //      num*2
  //    }
  //    twoTimes.map{ num =>
  //      if(num==1000000) {
  //        println(s"printing $num")
  //      }
  //    }
  //  }
  //  val ft2 = futTest2.map(_ => println("future completed"))
  //  val outSeries2 = 1 to 1000
  //  outSeries2.map(x => if(x ==500) println(x))
  //  println("Future test towards end line")
  //  Await.ready(ft2, Duration.Inf)
  //Thread.sleep(5000)
  //Test Future 2 completion ends


  /** Test Future 3 completion **/
  // futTest2 will be called 2 times. Better  use  above code for  Await
  //  def futTest2 = Future{
  //    println("in futTest")
  //    val series: Seq[Int] = 1 to 10000000
  //    val twoTimes = series.map{ num =>
  //      num*2
  //    }
  //    twoTimes.map{ num =>
  //      if(num==1000000) {
  //        println(s"printing $num")
  //      }
  //    }
  //  }
  //  futTest2.map(_ => println("future completed"))
  //  val outSeries2 = 1 to 1000
  //  outSeries2.map(x => if(x ==500) println(x))
  //  println("Future test towards end line")
  //  Await.ready(futTest2, Duration.Inf)
  //Thread.sleep(5000)
  //Test Future 3 completion ends


  /** Muliple  producer and consumer without await **/
//  def consFunc(id: Int, buffer: mutable.Queue[Int]) = {
//    val rand = new Random()
//    while (true) {
//      buffer.synchronized {
//        while(buffer.isEmpty){
//          buffer.wait()
//        }
//        val item = buffer.dequeue()
//        println(s"consumer $id consumed item $item")
//        buffer.notifyAll()
//      }
//
//      Thread.sleep(rand.nextInt(500))
//    }
//  }
//
//  def prodFunc(id: Int, buffer: mutable.Queue[Int], capacity: Int, qItem: AtomicInteger) = {
//    val rand = new Random()
//
//    while(true){
//      buffer.synchronized{
//        while(buffer.size == capacity){
//          buffer.wait()
//        }
//        buffer.enqueue(qItem.getAndIncrement())
//        val producedItem = buffer.last
//        println(s"procuder $id produces $producedItem")
//        buffer.notifyAll()
//      }
//    }
//    Thread.sleep(rand.nextInt(500))
//  }
//
//    def multiProCons(nCons: Int, nProd: Int, capacity: Int) = {
//      val buffer = new  mutable.Queue[Int]
//      val qItem  = new AtomicInteger(0)
//      (1 to nCons).foreach(i => Future(consFunc(i,buffer)))
//      (1 to nProd).foreach(i => Future(prodFunc(i, buffer, capacity, qItem)))
//    }
//
//  //println("starting multiProCons")
//  multiProCons(2,2,3)
//  //println("printing after multiProCons called")
//  Thread.sleep(5000)

  /** Muliple  producer and consumer without await ends**/


  /** Muliple  producer and consumer with await**/
  def consFunc(id: Int, buffer: mutable.Queue[Int]) = {
    val rand = new Random()
    while (true) {
      buffer.synchronized {
        while(buffer.isEmpty){
          buffer.wait()
        }
        val item = buffer.dequeue()
        println(s"consumer $id consumed item $item")
        buffer.notifyAll()
      }

      Thread.sleep(rand.nextInt(500))
    }
  }

  def prodFunc(id: Int, buffer: mutable.Queue[Int], capacity: Int, qItem: AtomicInteger) = {
    val rand = new Random()

    while(true){
      buffer.synchronized{
        while(buffer.size == capacity){ // why condition is in while loop and not in if() https://stackoverflow.com/a/10091092/3287419 .
          // When a waiting thread is woken up and acquires lock, the execution starts from the point where it went to wait().
          // If there was if() condition then when thread wakes up and acquires the lock, the execution won't check for condition and it will directly go to enqueue (or dequeue) statement. This may be a wrong thing to do.

          buffer.wait() // why wait and notify is in synchronized block(both in prod and cons [and also in general its mandatory I think]): 1)if wait is not synchronized block then more than 1 producer can wake at same time and start consuming (this is true only in case of notifyAll() I think, in case of notify() it may not be problem as only producer will wake up)
          // 2) more important reason https://javarevisited.blogspot.com/2011/05/wait-notify-and-notifyall-in-java.html
          // https://stackoverflow.com/questions/2779484/why-must-wait-always-be-in-synchronized-block
        }
        buffer.enqueue(qItem.getAndIncrement())
        val producedItem = buffer.last
        println(s"procuder $id produces $producedItem")
        buffer.notifyAll()
      }
      Thread.sleep(rand.nextInt(500))
    }
  }

  def multiProCons(nCons: Int, nProd: Int, capacity: Int) = {
    val buffer = new  mutable.Queue[Int]
    val qItem  = new AtomicInteger(0)
    val  conses: Seq[Future[Unit]] = (1 to nCons).map(i => Future(consFunc(i,buffer)))
    val prods: Seq[Future[Unit]] = (1 to nProd).map(i => Future(prodFunc(i, buffer, capacity, qItem)))
    Future.sequence(conses ++ prods)
  }

  //println("starting multiProCons")
  Await.ready(multiProCons(2,2,3), Duration.Inf)
  //println("printing after multiProCons called")
}
