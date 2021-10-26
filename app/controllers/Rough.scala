package controllers

import java.nio.file.{Files, Paths}
import java.nio.charset.StandardCharsets
import java.util.concurrent.Executors

import scala.concurrent.{Await, ExecutionContext, Future}

import scala.concurrent.duration.Duration

object Rough extends App {

  implicit val exec = ExecutionContext.fromExecutor(Executors.newCachedThreadPool) //This starts a new future in user thread and hence JVM waits for future to complete while shutting itself
  //import scala.concurrent.ExecutionContext.Implicits.global //This starts a new future in demon thread and hence JVM does not wait for future to complete while shutting itself
  def writeToFile() = Future {
    val toWrite = "write this 3"
    println("before sleep")
    Thread.sleep(1000)
    println("after sleep")
    Files.write(Paths.get("file.txt"), toWrite.getBytes(StandardCharsets.UTF_8))
  }

  writeToFile()
  //Await.ready(writeToFile(), Duration.Inf)

//    def makeRunnable(n: Int): Runnable = {() =>
//      println("scds")
//    }
}

