import AssemblyKeys._

// put this at the top of the file

name := "scala-ofx"

organization := "org.vvcephei"

version := "1.2"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % "1.0.3",
  "org.apache.httpcomponents" % "httpclient" % "4.2.1",
  "com.sun.jersey" % "jersey-core" % "1.17.1",
  "com.sun.jersey" % "jersey-client" % "1.17.1",
  "com.sun.jersey" % "jersey-json" % "1.17.1",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.10" % "2.2.3",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-joda" % "2.2.3",
  "org.joda" % "joda-convert" % "1.2",
  "joda-time" % "joda-time" % "2.3",
  "com.github.tototoshi" %% "scala-csv" % "1.3.1",
  "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % "2.1.3",
  "com.beust" % "jcommander" % "1.30",
  "org.apache.opennlp" % "opennlp-tools" % "1.5.3",
  "org.apache.opennlp" % "opennlp-maxent" % "3.0.3"
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)

mainClass := Some("org.vvcephei.scalaofx.cli.CLI")

net.virtualvoid.sbt.graph.Plugin.graphSettings

assemblySettings

mainClass in assembly := Some("org.vvcephei.scalaofx.cli.CLI")

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (version.value.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra :=
  <url>https://github.com/vvcephei/scala-ofx</url>
    <licenses>
      <license>
        <name>Apache</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:vvcephei/scala-ofx.git</url>
      <connection>scm:git:git@github.com:vvcephei/scala-ofx.git</connection>
    </scm>
    <developers>
      <developer>
        <id>vvcephei</id>
        <name>John Roesler</name>
        <url>http://www.vvcephei.org</url>
      </developer>
    </developers>


