name := "twitterStreams"

version := "0.1"

scalaVersion := "2.11.12"

val sparkVersion = "2.4.5"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
)

libraryDependencies += "org.apache.bahir" %% "spark-streaming-twitter" % "2.0.0"

//libraryDependencies += "org.apache.spark" %% "spark-core"  % "2.4.5"

//libraryDependencies += "org.apache.spark" % "spark-streaming_2.11" % "2.4.5" % "provided"

//libraryDependencies += "org.apache.spark" %% "spark-streaming-twitter" % "2.4.5"

