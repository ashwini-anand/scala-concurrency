package controllers

import java.util.concurrent.Executors

import play.api.libs.json.Json

import scala.concurrent.{ExecutionContext, Future}

object WorkTest extends App {

  implicit val exec = ExecutionContext.fromExecutor(Executors.newCachedThreadPool)

  println("WorkTest")

  def checkRights(ss: String, ss2: String) = Future{
    println("in checkrights before sleep")
    Thread.sleep(3000)
    println("in checkrights after sleep")
    true
  }

  def callIt = Future{
    //Thread.sleep(1000)
    println("in u, will return 1")
    1
  }

  def execute[A](chkRts: => Future[Boolean])(u: => Future[A]) = {
    Thread.sleep(2000)
    chkRts.flatMap{x =>
      //Thread.sleep(500)
      println(s"has right $x")
      //Thread.sleep(1000)
      u
    }
  }

  def testFunc() = {
    execute(checkRights("abcd", "acbd")){
      callIt
    }
  }

  testFunc().map{ res =>
    println(s"testFunc result $res")
  }

  case class JobStatus(name: String, id: Int, status: String)
  case class Job(name: String, id: Int);
  val job1 = Job("name1",1)
  val jobStatus1 = JobStatus("name1",1,"pss")
  val job2 = Job("name2",2)
  val jobStatus2 = JobStatus("name2",2,"pas")
  val jobStatus3: Option[JobStatus] = None
  val id1=1
  val id2 = 2
  implicit val jobFormat = Json.format[Job]
  implicit val jobStFormat = Json.format[JobStatus]
  def testJson()={
    val jobs= Seq(job1, job2)
    val jsv = jobs.map{job =>
      val jobId = job.id
      val jobJson = Json.toJson(job)
      val jobStatusJson = Json.toJson(jobStatus3)
      Json.parse(
        s"""
           |{
           |  "$jobId":{
           |    "job": $jobJson,
           |    "jobStatus": $jobStatusJson
           |  }
           |}
    """.stripMargin)
    }
    println(Json.toJson(jsv))
  }
  testJson()
}
