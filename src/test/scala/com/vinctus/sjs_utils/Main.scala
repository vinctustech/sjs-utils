package com.vinctus.sjs_utils

import Implicits._

object Main extends App {

  val m = map(a = 3, b = "asdf", c = map(d = 5, e = "zxcv"))

  val n: Int = m.a
  val s: String = m.b

  println(m, n, s)

}
