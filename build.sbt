name := "Akka project from UPenn class."

version := "1.0"

scalaVersion := "2.11.4"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.4"

libraryDependencies += "commons-io" % "commons-io" % "2.4"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

// Fork JVM when `run`-ing SBT
// http://stackoverflow.com/a/5265162/409976
fork in run := true