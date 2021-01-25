package com.vinctus.sjs_utils

object Implicits {

  implicit def product2seq(p: Product): Seq[(String, Any)] = p.productElementNames zip p.productIterator toSeq

  implicit def product2map(p: Product): Map[String, Any] = product2seq(p).toMap

  implicit def dotString(f: DynamicMapDot): String = f.asString

  implicit def dotInt(f: DynamicMapDot): Int = f.asInt

  implicit def dotDynamicMap(f: DynamicMapDot): DynamicMap = f.asMap

  implicit def dotMap(f: DynamicMapDot): Map[String, Any] = dotDynamicMap(f)

}
