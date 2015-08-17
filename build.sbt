// Name of the project
name := "Newtonian Universe"

// Project version
version := "1"

// Version of Scala used by the project
scalaVersion := "2.11.7"

// Add dependency on ScalaFX library
libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.40-R8"

// Fork a new JVM for 'run' and 'test:run', to avoid JavaFX double initialization problems
fork := true

mainClass in (Compile, run) := Some("Newton")
