package com.github.plippe.paintshop.io

import scala.io.Source
import org.scalatest.FunSuite

import com.github.plippe.paintshop.color.ColorFinish

class InputReaderSpec extends FunSuite {

  test("InputReader.fromFile should raise an error if file not found") {
    intercept[FileNotFoundException$] { InputReader.fromFile("/no/file") }
  }

  test("InputReader.fromFile should extract Input from a file") {
    val filePath = getClass.getResource("/input.txt").getPath

    val fromFile = InputReader.fromFile(filePath)
    val fromLines = {
      val file = Source.fromFile(filePath)
      val lines = file.getLines.toList
      file.close

      InputReader.fromLines(lines)
    }

    assert(fromFile == fromLines)
  }

  test("InputReader.fromLines should raise an error for an empty lines") {
    val lines = Seq.empty
    intercept[FileFormatNotValidException$] { InputReader.fromLines(lines) }
  }

  test("InputReader.isValidFormat should raise an error for no color count") {
    val lines = Seq("1 G")
    intercept[FileFormatNotValidException$] { InputReader.fromLines(lines) }
  }

  test("InputReader.isValidFormat should raise an error if color finish not correct") {
    val lines = Seq("1", "1 N")
    intercept[FileFormatNotValidException$] { InputReader.fromLines(lines) }
  }

  test("InputReader.isValidFormat should return empty orders if non are given") {
    val lines = Seq("1")
    val input = InputReader.fromLines(lines)

    assert(input.colorCount === 1)
    assert(input.customerOrders.size === 0)
  }

  test("InputReader.isValidFormat should return orders found") {
    val lines = Seq("1", "1 G", "2 G 3 M")
    val input = InputReader.fromLines(lines)

    assert(input.colorCount === 1)
    assert(input.customerOrders.size === 2)

    val customerOrders1 = input.customerOrders.toSeq(0)
    assert(customerOrders1.customer.id === 1)
    assert(customerOrders1.colors.size === 1)
    assert(customerOrders1.colors.toSeq(0).id === 1)
    assert(customerOrders1.colors.toSeq(0).finish === ColorFinish.Gloss)

    val customerOrders2 = input.customerOrders.toSeq(1)
    assert(customerOrders2.customer.id === 2)
    assert(customerOrders2.colors.size === 2)
    assert(customerOrders2.colors.toSeq(0).id === 2)
    assert(customerOrders2.colors.toSeq(0).finish === ColorFinish.Gloss)
    assert(customerOrders2.colors.toSeq(1).id === 3)
    assert(customerOrders2.colors.toSeq(1).finish === ColorFinish.Matte)
  }
}
