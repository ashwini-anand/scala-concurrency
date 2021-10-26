name := """play-java-hello-world-web"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.0"

libraryDependencies += guice
libraryDependencies ++= Seq("com.typesafe.akka" %% "akka-actor" % "2.5.26",
  "org.quartz-scheduler" % "quartz" % "2.3.1",
  "org.quartz-scheduler" % "quartz-jobs" % "2.3.1",
  "com.cronutils" % "cron-utils" % "2.0.0")
  
