enablePlugins(ScalaNativePlugin)

val snunitVersion = "0.0.5"

ThisBuild / scalaVersion := "2.13.4"

lazy val root = project
  .in(file("."))
  .aggregate(example.jvm, example.js, example.native)

lazy val example = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalameta" %%% "munit" % "0.7.21" % Test
    ),
    testFrameworks += new TestFramework("munit.Framework")
  )
  .jvmSettings(
  )
  .jsSettings(
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies += "com.raquo" %%% "laminar" % "0.11.0"
  )
  .nativeSettings(
    libraryDependencies ++= Seq(
      "com.github.lolgab" %%% "snunit" % snunitVersion,
      "com.github.lolgab" %%% "snunit-async" % snunitVersion,
      "com.github.lolgab" %%% "snunit-routes" % snunitVersion,
      "com.github.lolgab" %%% "snunit-autowire" % snunitVersion
    ),
    Test / nativeLinkStubs := true
  )
  .platformsSettings(NativePlatform, JSPlatform)(
    libraryDependencies += "com.github.lolgab" %%% "autowire" % "0.3.2"
  )
  .platformsSettings(JVMPlatform, JSPlatform)(
    libraryDependencies += "com.lihaoyi" %%% "upickle" % "1.2.2"
  )
  .platformsSettings(NativePlatform, JVMPlatform)(
    Compile / unmanagedSourceDirectories += baseDirectory.value / ".." / "native-jvm" / "src" / "main" / "scala",
    Test / unmanagedSourceDirectories += baseDirectory.value / ".." / "native-jvm" / "src" / "test" / "scala"
  )
