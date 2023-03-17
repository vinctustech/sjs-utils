package com.vinctus.sjs_utils

import Implicits._

import scalajs.js

@main def run(): Unit =
  def stringify(x: Any) = js.JSON.stringify(x.asInstanceOf[js.Any], null.asInstanceOf[js.Array[js.Any]], 2)

  val o = js.Dynamic.literal(a = 3, b = js.undefined)

  println(stringify(o))

  println(o.asInstanceOf[js.Dictionary[js.Any]].iterator.mkString(", "))
  val m = toMap(o, false)

  println(m)
  println(stringify(toJS(m)))

  println(fromJS(js.undefined, false))
