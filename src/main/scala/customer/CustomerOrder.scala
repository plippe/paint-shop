package com.github.plippe.paintshop.customer

import com.github.plippe.paintshop.color.Color

case class CustomerOrder(
    customer: Customer,
    colors: Iterable[Color]
) {

  def acceptableColors(proposedColors: Iterable[Color]): Boolean = {
    colors.exists { color =>
      proposedColors.exists { proposedColor =>
        color.id == proposedColor.id &&
          color.finish == proposedColor.finish
      }
    }
  }

}
