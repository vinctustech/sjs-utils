package com.vinctus.sjs_utils

object Implicits {

  implicit def product2seq(p: Product): Seq[(String, Any)] = p.productElementNames zip p.productIterator toSeq

  implicit def product2map(p: Product): Map[String, Any] = product2seq(p).toMap

  implicit def dotString(f: DynamicMapDot): String = f.map(f.field).toString

  implicit def dotInt(f: DynamicMapDot): Int = f.map(f.field).asInstanceOf[Int]

  implicit def dotDynamicMap(f: DynamicMapDot): DynamicMap = f.map(f.field).asInstanceOf[DynamicMap]

  implicit def dotMap(f: DynamicMapDot): Map[String, Any] = dotDynamicMap(f)

}
