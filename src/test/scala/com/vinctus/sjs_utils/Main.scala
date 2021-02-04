package com.vinctus.sjs_utils

import Implicits._

object Main extends App {

  case class CC(d: Int, e: String)

  val m = map(a = 3, b = "asdf", c = CC(5, "zxcv"), d = List(1, 2, 3))

  val n: Int = m.c.d
  val s: String = m.c.e

  println(n, s, m)

}
