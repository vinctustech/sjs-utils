addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.6")

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.5.0")

resolvers += Resolver.bintrayRepo("oyvindberg", "converter")

addSbtPlugin("org.scalablytyped.converter" % "sbt-converter" % "1.0.0-beta31")

resolvers += Resolver.bintrayRepo("edadma", "sbt-plugins")

addSbtPlugin("xyz.hyperreal" % "npm-plugin" % "0.1.13")
