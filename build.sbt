ThisBuild / scalaVersion     := "2.12.12"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "artikus.co.uk"
ThisBuild / organizationName := "Artikus"

testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a"))

// parallelExecution := true,    parallelExecution := true,

lazy val commons = Seq(
    crossPaths := false,
    fork in Test := true,
    parallelExecution in Test := false,
    javacOptions  ++= Seq(
      "-Xlint:unchecked", "-Xlint:deprecation", "-Xdiags:verbose"),
    scalacOptions ++= Seq(
      "-encoding", "UTF-8", 
      "-deprecation", "-unchecked", "-feature", "-Xlint",
      "-Ywarn-infer-any", 
      "-language:experimental.macros")
  )

lazy val spark = (project in file("spark-eg0"))
  .settings(
    	name := "Scala/R/Python interworking",
	commons
  )

lazy val eg0 = (project in file("prog-scala-2nd-ed-code-examples"))
  .settings(
    	name := "Prog-Scala",
	commons
  )

//  .dependsOn(spark)

lazy val root = (project in file("."))
  .aggregate(spark, eg0)

mainClass in (Compile, run) := Some("progscala2.fp.loops.Factorial")

// Uncomment the following for publishing to Sonatype.
// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for more detail.

// ThisBuild / description := "Some descripiton about your project."
// ThisBuild / licenses    := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
// ThisBuild / homepage    := Some(url("https://github.com/example/project"))
// ThisBuild / scmInfo := Some(
//   ScmInfo(
//     url("https://github.com/your-account/your-project"),
//     "scm:git@github.com:your-account/your-project.git"
//   )
// )
// ThisBuild / developers := List(
//   Developer(
//     id    = "Your identifier",
//     name  = "Your Name",
//     email = "your@email",
//     url   = url("http://your.url")
//   )
// )
// ThisBuild / pomIncludeRepository := { _ => false }
// ThisBuild / publishTo := {
//   val nexus = "https://oss.sonatype.org/"
//   if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
//   else Some("releases" at nexus + "service/local/staging/deploy/maven2")
// }
// ThisBuild / publishMavenStyle := true
