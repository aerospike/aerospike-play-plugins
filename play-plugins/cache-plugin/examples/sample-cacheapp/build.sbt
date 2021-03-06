name := """sample-cacheapp"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "com.aerospike" % "aerospike-play-cache_2.11" % "1.2",
  "org.projectlombok" % "lombok" % "1.16.4"
)
routesGenerator := InjectedRoutesGenerator
