package com.vinctus

package object sjs_utils {

  def map2cc[T](map: Map[Any, Any], cc: Any): T =
    (map.values.toList match {
      case List(a)                  => cc.asInstanceOf[Function1[Any, _]](a)
      case List(a1, a2)             => cc.asInstanceOf[Function2[Any, Any, _]].tupled(a1, a2)
      case List(a1, a2, a3)         => cc.asInstanceOf[Function3[Any, Any, Any, _]].tupled(a1, a2, a3)
      case List(a1, a2, a3, a4)     => cc.asInstanceOf[Function4[Any, Any, Any, Any, _]].tupled(a1, a2, a3, a4)
      case List(a1, a2, a3, a4, a5) => cc.asInstanceOf[Function5[Any, Any, Any, Any, Any, _]].tupled(a1, a2, a3, a4, a5)
    }).asInstanceOf[T]

}
