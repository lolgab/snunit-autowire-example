val snunitVersion = "0.0.20"
val tapirVersion = "1.0.0-RC3"
val sttpVersion = "3.6.2"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = project
  .in(file("."))
  .aggregate(domain.jvm, domain.js, domain.native)
  .aggregate(api.jvm, api.js, api.native)
  .aggregate(frontend)
  .aggregate(backend.jvm, backend.native)

lazy val domain = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .settings()

lazy val api = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .dependsOn(domain)
  .settings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "upickle" % "2.0.0",
      "com.outr" %%% "scribe" % "3.6.3",
      "com.softwaremill.sttp.tapir" %%% "tapir-json-upickle" % tapirVersion,
      "com.softwaremill.sttp.tapir" %%% "tapir-core" % tapirVersion,
      "org.scalameta" %%% "munit" % "1.0.0-M4" % Test
    )
  )

lazy val frontend = project
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(api.js)
  .settings(
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client3" %%% "core" % sttpVersion,
      "com.softwaremill.sttp.tapir" %%% "tapir-sttp-client" % tapirVersion,
      "com.raquo" %%% "laminar" % "0.14.2"
    )
  )

lazy val backend = crossProject(JVMPlatform, NativePlatform)
  .dependsOn(api)
  .settings(
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client3" %%% "core" % sttpVersion,
    ),
    testFrameworks += new TestFramework("munit.Framework")
  )
  .platformsSettings(NativePlatform, JVMPlatform)(
    libraryDependencies ++= Seq(
      "com.github.lolgab" %%% "snunit-tapir" % snunitVersion
    )
  )
  .jvmSettings(
  )
  .nativeSettings(
    libraryDependencies ++= Seq(
      "com.github.david-bouyssie" %%% "sqlite4s" % "0.4.1"
    ),
    Test / nativeLinkStubs := true
  )
