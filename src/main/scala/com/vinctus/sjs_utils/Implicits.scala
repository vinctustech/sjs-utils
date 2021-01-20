package com.vinctus.sjs_utils

import scala.collection.immutable.ListMap

object Implicits {

  implicit def product2seq(p: Product): Seq[(String, Any)] = p.productElementNames zip p.productIterator toSeq

  implicit def product2map(p: Product): Map[String, Any] = product2seq(p).toMap

  //implicit def product2dynamic(p: Product): DynamicMap = new DynamicMap(p to ListMap)

}
