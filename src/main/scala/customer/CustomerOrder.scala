package com.github.plippe.paintshop.customer

import com.github.plippe.paintshop.color.Color

case class CustomerOrder(
  customer: Customer,
  colors: Iterable[Color]
)
