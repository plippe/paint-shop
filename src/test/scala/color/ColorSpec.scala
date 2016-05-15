package com.github.plippe.paintshop.color

import org.scalatest.FunSuite

class ColorSpec extends FunSuite {

  test("Color.price should return the finish price") {
    assert(Color(1, ColorFinish.Gloss).price == ColorFinish.Gloss.price)
    assert(Color(1, ColorFinish.Matte).price == ColorFinish.Matte.price)
  }

}
