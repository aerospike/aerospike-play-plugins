name := """aerospike-play-cache"""

version := "1.0"

organization := "com.aerospike"

// Enables publishing to maven repo
publishMavenStyle := true

// Do not append Scala versions to the generated artifacts
//crossPaths := false

// This forbids including Scala related libraries into the dependency
autoScalaLibrary := false
crossScalaVersions := Seq("2.11.6", "2.10.5")
scalaVersion := "2.11.6"

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.4.0" % "provided",
  "com.typesafe.play" %% "play-cache" % "2.4.0",
   "org.apache.commons" % "commons-lang3" % "3.0.1",
   "org.projectlombok" % "lombok" % "1.16.4",
  "com.aerospike" % "aerospike-cache" % "0.9-SNAPSHOT"
)


pomExtra := {
  <scm>
    <url>https://github.com/typesafehub/play-plugins</url>
    <connection>scm:git:git@github.com:typesafehub/play-plugins.git</connection>
  </scm>
  <developers>
    <developer>
      <id>aerospike</id>
      <name>Aerospike</name>
      <url>https://aerospike.com</url>
    </developer>
  </developers>
}
pomIncludeRepository := { _ => false }
homepage := Some(url(s"https://github.com/typesafehub/play-ulgins"))
licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))

sonatypeProfileName := "com.aerospikePlayCache"
releasePublishArtifactsAction := PgpKeys.publishSigned.value
releaseTagName := s"aerospike-play-cache-${(version in ThisBuild).value}"
releaseCrossBuild := true

import ReleaseTransformations._
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  releaseStepCommand("sonatypeRelease"),
  setNextVersion,
  commitNextVersion,
  pushChanges
)