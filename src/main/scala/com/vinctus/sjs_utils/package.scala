package com.vinctus

import java.time.{Instant, LocalDate}
import scala.collection.immutable.ListMap
import scala.scalajs.js
import js.JSConverters._

package object sjs_utils {

  def map2cc[T: Mappable](map: Map[String, Any]): T =
    implicitly[Mappable[T]].fromMap(map)

  def jsArray(v: Any): Boolean = v.isInstanceOf[js.Array[_]]

  def jsObject(v: Any): Boolean =
    js.typeOf(v) == "object" && (v != null) && !v.isInstanceOf[Long] && !v.isInstanceOf[js.Date] && !jsArray(v)

  def toMap(obj: js.UndefOr[js.Any]): ListMap[String, Any] = {
    def toMap(obj: js.Any): ListMap[String, Any] = {
      var map: ListMap[String, Any] = obj.asInstanceOf[js.Dictionary[js.Any]].to(ListMap)

      for ((k, v) <- map)
        if (jsObject(v))
          map = map + ((k, toMap(v.asInstanceOf[js.Any])))
        else if (jsArray(v))
          map = map + ((k, v.asInstanceOf[js.Array[_]].toList))

      map
    }

    if (!obj.isDefined) null
    else toMap(obj)
  }

  def toJS(a: Any): js.Any =
    a match {
      case Some(a)          => a.asInstanceOf[js.Any]
      case None             => js.undefined
      case date: LocalDate  => new js.Date(date.getYear, date.getMonthValue - 1, date.getDayOfMonth)
      case instant: Instant => new js.Date(instant.toEpochMilli.toDouble)
      case d: BigDecimal    => d.toDouble
      case l: Seq[_]        => l map toJS toJSArray
      case m: Map[_, _] =>
        (m map { case (k, v) => k -> toJS(v) })
          .asInstanceOf[Map[String, Any]]
          .toJSDictionary
      case _ => a.asInstanceOf[js.Any]
    }

}
