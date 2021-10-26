package controllers

import java.util.concurrent.atomic.AtomicInteger

import play.mvc.BodyParser.Json

import scala.collection.mutable
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

object TraitTest extends App {

  trait abc {
    def fub1(): Unit = {
      println("abc")
    }
  }

  trait bcd {
    def fub1(): Unit = {
      println("bcd")
    }
  }

  class sdf() extends bcd //with abc
  {
    fub1()
  }

  val sd = new sdf()
  sd.fub1()
  val x = 1;
  val funfun: Int => (Int => Int) = (x: Int) => {
    val fun2 = (y: Int) => {
      x + y
    }
    fun2
  }

  val abc = funfun(1)(3)

  // Partial Function
  val divide = new PartialFunction[Int, Int] {
    override def apply(v1: Int): Int = 50 / v1

    override def isDefinedAt(x: Int): Boolean = x != 0
  }

  def partialFuncDemo(x: Int) = {
    if (divide.isDefinedAt(x)) println(divide.apply(x)) else println("divide not defined")
  }

  partialFuncDemo(0)
  partialFuncDemo(10)

  //Partial function over

  //  Higher order functions example starts

  def addition(f: (Int, Int) => Int,a: Int, b:Int): Int = f(a,b)

  val squareSum = (x: Int, y: Int) => (x*x + y*y)
  val cubeSum = (x: Int, y: Int) => (x*x*x + y*y*y)

  val squaredSum = addition(squareSum, 1, 2)
  val cubedSum = addition(cubeSum, 1, 2)

  //  Higher order functions example ends


  // curry (curried) function example1
  def currFun(x: Int): Int => Int = {
    val inner = (y: Int) => {
      x + y
    }
    inner
  }

  println("Curried function sum is " + currFun(10)(5))

  // curry (curried) function example2
  def currFun2(x: Int): Int => Int = {
    def inner(y: Int) = {
      x + y
    }

    inner
  }

  val currex = currFun(10)
  val res = currex(2)

  //curry over

  // closure  example
  def closureFunc(ii: Int): Int => Int = {
    val i = 3
    def retFunc(k : Int) = i * ii + k
    retFunc
  }

  val cl = closureFunc(5)
  println("output of closureFunc "+cl(3))
  // closure example over

  val abc2 = new Thread(() => println(""))
  abc2.start()

  val abc3 = Future {
    1
  }
  abc3.map { res => println(res) }
  abc3.map { res => println("reading 2nd time " + res) }

  //Example of adding a method to library class

  implicit class Int2String(x: Int) {
    def int2Str = x.toString
  }

  //  class Int2String(x: Int){
  //    def int2Str = x.toString
  //  }
  //  implicit def converterStr(x: Int) = new Int2String(x)
  val x1 = 1
  val y1: String = x1.int2Str

  //Finished example of adding a method to library class

  trait Convert[A] {
    def convertTo(value: A): Json
  }

  // producer consumer starts
  class Consumer(id: Int, buffer: mutable.Queue[Int]) extends Thread {
    override def run(): Unit = {
      val random = new Random()

      while (true) {
        buffer.synchronized {
          while (buffer.isEmpty) {
            buffer.wait()
          }
          val x = buffer.dequeue()
          println(s"consumer $id consumed item " + x)
          buffer.notifyAll()

        }

        Thread.sleep(random.nextInt(250))
      }
    }
  }

  class Producer(id: Int, buffer: mutable.Queue[Int], capacity: Int) extends Thread {
    override def run(): Unit = {
      val random = new Random()
      var i = 0
      while (true) {
        buffer.synchronized {
          while (buffer.size == capacity) {
            buffer.wait()
          }
          println(s"producer $id producing " + i)
          buffer.enqueue(i)
          buffer.notifyAll()
          i += 1
        }

        Thread.sleep(random.nextInt(250))
      }
    }
  }

  def multiProCons(nCons: Int, nProd: Int, capacity: Int) = {
    val buffer = new mutable.Queue[Int]

    (1 to nCons).foreach(i => new Consumer(i, buffer).start())
    (1 to nProd).foreach(i => new Producer(i, buffer, capacity).start())

  }

  multiProCons(3, 3, 4)

  //  class Consumer(id: Int, buffer: mutable.Queue[Int]) extends Thread{
  //    override def run(): Unit = {
  //      val  random = new Random()
  //
  //      while(true){
  //        buffer.synchronized{
  //          while(buffer.isEmpty){
  //            buffer.wait()
  //          }
  //          val x = buffer.dequeue()
  //          println(s"consumer $id consumed item "+x)
  //          buffer.notifyAll()
  //
  //        }
  //
  //        Thread.sleep(random.nextInt(250))
  //      }
  //    }
  //  }
  //
  //  class Producer(id: Int, buffer: mutable.Queue[Int], capacity: Int, qItem: AtomicInteger) extends  Thread{
  //    override def run(): Unit = {
  //      val random = new Random()
  //      while(true){
  //        buffer.synchronized{
  //          while(buffer.size == capacity){
  //            buffer.wait()
  //          }
  //          println(s"producer $id producing "+qItem.get())
  //          buffer.enqueue(qItem.getAndIncrement())
  //          buffer.notifyAll()
  //        }
  //
  //        Thread.sleep(random.nextInt(250))
  //      }
  //    }
  //  }
  //
  //  def multiProCons(nCons: Int, nProd: Int, capacity: Int) = {
  //    val buffer = new  mutable.Queue[Int]
  //    val qItem  = new AtomicInteger(0)
  //    (1 to nCons).foreach(i => new Consumer(i, buffer).start())
  //    (1 to nProd).foreach(i => new Producer(i, buffer, capacity, qItem).start())
  //
  //  }
  //
  //  multiProCons(3,3,4)

  //  class Consumer(id: Int, buffer: mutable.Queue[Int]) extends Thread{
  //    override def run(): Unit = {
  //      val  random = new Random()
  //
  //      while(true){
  //        buffer.synchronized{
  //          while(buffer.isEmpty){
  //            buffer.wait()
  //          }
  //          val x = buffer.dequeue()
  //          println(s"consumer $id consumed item "+x)
  //          buffer.notifyAll()
  //
  //        }
  //
  //        Thread.sleep(random.nextInt(250))
  //      }
  //    }
  //  }
  //
  //  class Producer(id: Int, buffer: mutable.Queue[Int], capacity: Int, qItem: Item) extends  Thread{
  //    override def run(): Unit = {
  //      val random = new Random()
  //      while(true){
  //        buffer.synchronized{
  //          while(buffer.size == capacity){
  //            buffer.wait()
  //          }
  //          println(s"producer $id producing "+qItem.item)
  //          buffer.enqueue(qItem.item.getAndIncrement())
  //          buffer.notifyAll()
  //        }
  //
  //        Thread.sleep(random.nextInt(250))
  //      }
  //    }
  //  }
  //
  //  case class Item(item: AtomicInteger)
  //  def multiProCons(nCons: Int, nProd: Int, capacity: Int) = {
  //    val buffer = new  mutable.Queue[Int]
  //    val qItem  = Item(new AtomicInteger(0))
  //    (1 to nCons).foreach(i => new Consumer(i, buffer).start())
  //    (1 to nProd).foreach(i => new Producer(i, buffer, capacity, qItem).start())
  //
  //  }
  //
  //  multiProCons(3,3,4)

  //call by name  vs call by value  starts
  def getTimeByValue(x: Long) = {
    println(s"printing time call by value $x")
    Thread.sleep(500)
    println(s"printing time call by value $x")
  }
  def getTimeByName( x: => Long) = {
    println(s"printing time call by name $x")
    Thread.sleep(500)
    println(s"printing time call by name $x")
  }

  getTimeByValue(System.currentTimeMillis())
  getTimeByName(System.currentTimeMillis())

  //call by name  vs call by value ends
}
