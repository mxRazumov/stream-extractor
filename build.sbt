name := """stream-extractor"""

version := "1.0"

scalaVersion := "2.12.6"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies += "com.opentable.components" % "otj-pg-embedded" % "0.12.0" % Test
libraryDependencies += "junit" % "junit" % "4.12" % Test

