package com.github.plippe.paintshop.customer

import org.scalatest.FunSuite

import com.github.plippe.paintshop.color._

class CustomerOrderSpec extends FunSuite {

  val gloss1 = Color(1, ColorFinish.Gloss)
  val gloss2 = Color(2, ColorFinish.Gloss)
  val gloss3 = Color(3, ColorFinish.Gloss)

  val matte1 = Color(1, ColorFinish.Matte)
  val matte2 = Color(2, ColorFinish.Matte)
  val matte3 = Color(3, ColorFinish.Matte)

  test("CustomerOrder.acceptableColors should return true if at least one proposed color matches one requested color") {
    val proposedColor = Seq(gloss1, gloss2, gloss3)
    val requestedColor = Seq(matte1, matte2, gloss3)
    val order = CustomerOrder(Customer(1), requestedColor)

    assert(order.acceptableColors(proposedColor) === true)
  }

  test("CustomerOrder.acceptableColors should return false if no proposed color matches any requested color") {
    val proposedColor = Seq(gloss1, gloss2, gloss3)
    val requestedColor = Seq(matte1, matte2, matte3)
    val order = CustomerOrder(Customer(1), requestedColor)

    assert(order.acceptableColors(proposedColor) === false)
  }
}
