lazy val root = (project in file(".")).
  settings(
    name := "paint-shop",
    version := "0.0.1",
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq(
      "com.beachape" %% "enumeratum" % "1.4.4",
      "org.scalactic" %% "scalactic" % "2.2.6",
      "org.scalatest" %% "scalatest" % "2.2.6" % "test")
    )
