package com.vinctus.sjs_utils

import scala.collection.immutable.{AbstractMap, ListMap}

object map extends Dynamic {
  def applyDynamicNamed(method: String)(properties: (String, Any)*): DynamicMap =
    method match {
      case "apply" =>
        if (properties.exists(_._1 == ""))
          sys.error(s"map contains empty property name: $properties")

        new DynamicMap(properties to ListMap)
    }
}

class DynamicMap(obj: ListMap[String, Any]) extends AbstractMap[String, Any] with Dynamic {
  def selectDynamic(field: String): String = obj(field).asInstanceOf[String]

  def removed(key: String): DynamicMap = new DynamicMap(obj.removed(key))

  def get(key: String): Option[Any] = obj.get(key)

  def iterator: Iterator[(String, Any)] = obj.iterator

  def updated[V1 >: Any](key: String, value: V1): DynamicMap = new DynamicMap(obj.updated(key, value))

  override def toString: String = obj.map { case (k, v) => s"$k: $v" }.mkString("{", ", ", "}")
}
