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

class DynamicMapField(val obj: Either[DynamicMap, Product], val field: String) extends Dynamic {
  def selectDynamic(subfield: String): DynamicMapField =
    new DynamicMapField(get match {
      case m: DynamicMap => Left(m)
      case p: Product    => Right(p)
    }, subfield)

  def get: Any =
    obj match {
      case Left(m)  => m(field)
      case Right(p) => p.productElement(p.productElementNames.indexOf(field))
    }

  def asString: String = get.asInstanceOf[String]

  def asInt: Int = get.asInstanceOf[Int]

  def asMap: DynamicMap = get.asInstanceOf[DynamicMap]

  def asList: List[DynamicMap] = get.asInstanceOf[List[DynamicMap]]

  override def toString: String = s"DynamicMap field: $obj . $field"
}

class DynamicMap(obj: ListMap[String, Any]) extends AbstractMap[String, Any] with Dynamic {
//  def selectDynamic(field: String): String = obj(field).asInstanceOf[String]
  def selectDynamic(field: String): DynamicMapField = new DynamicMapField(Left(this), field)

  def >>>(field: String): DynamicMap = obj(field).asInstanceOf[DynamicMap]

  def >>(field: String): String = obj(field).asInstanceOf[String]

  def removed(key: String): DynamicMap = new DynamicMap(obj.removed(key))

  def get(key: String): Option[Any] = obj.get(key)

  def iterator: Iterator[(String, Any)] = obj.iterator

  def updated[V1 >: Any](key: String, value: V1): DynamicMap = new DynamicMap(obj.updated(key, value))

  private def quoted(a: Any) =
    a match {
      case s: String => s"${'"'}$s${'"'}"
      case _         => String.valueOf(a)
    }

  override def toString: String = obj.map { case (k, v) => s"$k: ${quoted(v)}" }.mkString("{", ", ", "}")
}
