name := "Darwin"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.11.8"

val scalaTestVersion = "2.2.4"
//val akkaGroup = "com.typesafe.akka"
//val akkaVersion = "2.4.1"

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += Resolver.sonatypeRepo("public")
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
	"com.phasmid" %% "lascala" % "1.0.8-SNAPSHOT",
//	"org.scala-lang.modules" %% "scala-xml" % "1.0.4",
//	"org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
	"org.scalatest" %% "scalatest" % scalaTestVersion % "test"
//	akkaGroup %% "akka-actor" % akkaVersion,
//	"org.ccil.cowan.tagsoup" % "tagsoup" % "1.2.1"
)

//val sprayGroup = "io.spray"
//val sprayVersion = "1.3.3"
//val sprayJsonVersion = "1.3.2"
//libraryDependencies ++= List("spray-client") map {c => sprayGroup %% c % sprayVersion}
//libraryDependencies ++= List("spray-json") map {c => sprayGroup %% c % sprayJsonVersion}
