package com.github.plippe.paintshop.io

import org.scalatest.FunSuite

import com.github.plippe.paintshop.color._

class OutputSpec extends FunSuite {

  test("Output.fromColors should return empty for empty color list") {
    assert(Output.fromColors(Seq.empty) === Output.empty)
  }

  test("Output.fromColors should return color finishes sorted by color id") {
    val colors = Seq(
      Color(3, ColorFinish.Gloss),
      Color(1, ColorFinish.Gloss),
      Color(2, ColorFinish.Matte)
    )

    val ouput = Output.fromColors(colors)
    assert(ouput.text == "G M G")
  }
}
