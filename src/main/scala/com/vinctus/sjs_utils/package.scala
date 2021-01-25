package com.vinctus

package object sjs_utils {

  def map2cc[T](map: Map[Any, Any], cc: Any): T =
    (map.values.toList match {
      case Nil                      => sys.error("empty map")
      case List(a)                  => cc.asInstanceOf[Any => _](a)
      case List(a1, a2)             => cc.asInstanceOf[(Any, Any) => _].tupled(a1, a2)
      case List(a1, a2, a3)         => cc.asInstanceOf[(Any, Any, Any) => _].tupled(a1, a2, a3)
      case List(a1, a2, a3, a4)     => cc.asInstanceOf[(Any, Any, Any, Any) => _].tupled(a1, a2, a3, a4)
      case List(a1, a2, a3, a4, a5) => cc.asInstanceOf[(Any, Any, Any, Any, Any) => _].tupled(a1, a2, a3, a4, a5)
    }).asInstanceOf[T]

}
