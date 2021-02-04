package com.vinctus

import scala.collection.immutable.ListMap
import scala.scalajs.js

package object sjs_utils {

  def map2cc[T: Mappable](map: Map[String, Any]): T =
    implicitly[Mappable[T]].fromMap(map)

  def jsObject(v: Any): Boolean =
    js.typeOf(v) == "object" && (v != null) && !v.isInstanceOf[Long] && !v.isInstanceOf[js.Date] && !v
      .isInstanceOf[js.Array[_]]

  def toMap(obj: js.UndefOr[js.Any]): ListMap[String, Any] = {
    def toMap(obj: js.Any): ListMap[String, Any] = {
      var map: ListMap[String, Any] = obj.asInstanceOf[js.Dictionary[js.Any]].to(ListMap)

      for ((k, v) <- map)
        if (jsObject(v))
          map = map + ((k, toMap(v.asInstanceOf[js.Any])))

      map
    }

    if (!obj.isDefined) null
    else toMap(obj)
  }

}
