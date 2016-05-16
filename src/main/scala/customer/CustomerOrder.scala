package com.github.plippe.paintshop.customer

import com.github.plippe.paintshop.color.Color

case class CustomerOrder(
    customer: Customer,
    colors: Iterable[Color]
) {

  /**
   * Compare a set of colors with the colors requested in the order to see if
   * they would please the customer
   *
   * @param proposedColors colors to compare with the ones requested
   * @return true if the customer would be pleased to receive those colors
   */
  def acceptableColors(proposedColors: Iterable[Color]): Boolean = {
    colors.exists { color =>
      proposedColors.exists { proposedColor =>
        color.id == proposedColor.id &&
          color.finish == proposedColor.finish
      }
    }
  }

}
