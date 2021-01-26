package com.vinctus

package object sjs_utils {

  def map2cc[T: Mappable](map: Map[String, Any]): T =
    implicitly[Mappable[T]].fromMap(map)

}
