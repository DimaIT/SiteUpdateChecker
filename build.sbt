assemblyJarName in assembly := "siteUpdateChecker.jar"

mainClass in Compile := Some("root.Main")

libraryDependencies ++= Seq("org.jsoup" % "jsoup" % "1.7.2",
  "org.json4s" % "json4s-native_2.11" % "3.2.11")

version := "1.0"

scalaVersion := "2.11.7"
    