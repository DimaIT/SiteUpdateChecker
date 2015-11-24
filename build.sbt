assemblyJarName in assembly := "siteUpdateChecker.jar"

mainClass in Compile := Some("Main")

libraryDependencies += "org.jsoup" % "jsoup" % "1.7.2"

version := "1.0"

scalaVersion := "2.11.7"
    