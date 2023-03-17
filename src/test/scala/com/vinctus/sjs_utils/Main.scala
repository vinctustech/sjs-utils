package com.vinctus.sjs_utils

import Implicits._

import scalajs.js

@main def run(): Unit =
  def stringify(x: Any) = js.JSON.stringify(x.asInstanceOf[js.Any], null.asInstanceOf[js.Array[js.Any]], 2)

  val m = new DynamicMap(toMap(js.Dynamic.literal(a = 3, b = 4, c = 5, d = 6, e = 7)))

  println(m)
  println(stringify(toJS(m)))
