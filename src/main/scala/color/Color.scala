package com.github.plippe.paintshop.color

case class Color(
    id: Int,
    finish: ColorFinish
) {

  val price = finish.price

}
