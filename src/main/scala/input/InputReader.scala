package com.github.plippe.paintshop.input

import scala.io.Source

import com.github.plippe.paintshop.color._
import com.github.plippe.paintshop.customer._

case object FileNotFoundException extends Exception
case object FileFormatException extends Exception

object InputReader {

  def fromFile(filePath: String): Input = {
    try {
      val file = Source.fromFile(filePath)
      val lines = file.getLines.toList
      file.close

      InputReader.fromLines(lines)
    } catch {
      case e: java.io.FileNotFoundException => throw FileNotFoundException
    }
  }

  def fromLines(lines: Iterable[String]): Input = {
    try {
      val colorCount = lines.head.toInt
      val customerOrders = lines.tail
        .zipWithIndex
        .map {
          case (line, lineIndex) =>
            val customer = Customer(lineIndex + 1)

            val colorAndFinishPairs = line.split(" ").sliding(2, 2)
            val colors = colorAndFinishPairs.map {
              case Array(colorId, colorFinish) =>
                Color(colorId.toInt, ColorFinish.withName(colorFinish))
            }

            CustomerOrder(customer, colors.toIterable)
        }

      Input(colorCount.toInt, customerOrders)
    } catch {
      case e: Throwable => throw FileFormatException
    }
  }
}
