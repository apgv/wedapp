import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "wedapp"
  val appVersion      = "1.0-SNAPSHOT"

  val casbah = "org.mongodb" %% "casbah" % "2.5.0"
  val jodatime= "joda-time" % "joda-time" % "2.1"
  val json4sJackson = "org.json4s" %% "json4s-jackson" % "3.1.0"
	val bcryptScala = "com.github.t3hnar" % "scala-bcrypt_2.10" % "2.1"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    casbah,
    jodatime,
    json4sJackson,
		bcryptScala
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
