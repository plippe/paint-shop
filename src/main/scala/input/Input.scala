package com.github.plippe.paintshop.input

import com.github.plippe.paintshop.customer.CustomerOrder

case class Input(
  colorCount: Int,
  customerOrders: Iterable[CustomerOrder]
)
