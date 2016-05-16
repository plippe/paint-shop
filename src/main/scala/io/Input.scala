package com.github.plippe.paintshop.io

import com.github.plippe.paintshop.customer.CustomerOrder

case class Input(
  colorCount: Int,
  customerOrders: Iterable[CustomerOrder]
)
