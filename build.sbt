import dev.i10416.scalajsdevtool.BuildMode
ThisBuild / scalaVersion := "2.13.6"
ThisBuild / version := "0.0.1-SNAPSHOT"

lazy val client = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin, ScalaJSDevToolPlugin)
  .settings(
    name := "laika-demo",
    DevTool / baseHTML := Some(
      (Compile / resourceDirectory).value / "index.html"
    ),
    mode := BuildMode.Prod,
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "com.raquo" %%% "laminar" % "0.13.1",
      "com.github.japgolly.scalacss" %%% "core" % "0.8.0-RC1",
      "org.planet42" %%% "laika-core" % "0.18.0"
    )
  )
