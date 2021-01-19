package com.vinctus.sjs_utils

object Main extends App {

  val m = map(a = 3, b = "asdf", c = map(d = 5, e = "zxcv"))

  println(m >>> "c" >> "e")

}
