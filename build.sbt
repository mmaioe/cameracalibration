lazy val root = Project( id="cameracalibration",  base=file(".")).
  settings(
    name := "cameracalibration",
    version := "1.0",
    scalaVersion := "2.10.4",
    organization := "mmaioe.com",
    libraryDependencies ++= Seq(
      "org.scalanlp" %% "breeze" % "0.11.2" ,
      // native libraries are not included by default. add this if you want them (as of 0.7)
      // native libraries greatly improve performance, but increase jar sizes.
      // It also packages various blas implementations, which have licenses that may or may not
      // be compatible with the Apache License. No GPL code, as best I know.
      "org.scalanlp" %% "breeze-natives" % "0.11.2",
      // the visualization library is distributed separately as well.
      // It depends on LGPL code.
      "org.scalanlp" %% "breeze-viz" % "0.11.2",
      // LAPACK
      "com.googlecode.netlib-java" % "netlib-java" % "1.0"
    )
  )
