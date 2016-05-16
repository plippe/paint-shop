package com.github.plippe.paintshop

import com.github.plippe.paintshop.io._
import com.github.plippe.paintshop.color.Color
import com.github.plippe.paintshop.solutions.BruteForce

/** Mockable trait to test application ouput */
object Main extends Main with OutputWriter
trait Main {
  def print(toOutput: Any): Unit

  val usage = """
    |missing FILE
    |Usage: sbt "run [FILE]"
  """.stripMargin.trim

  def fileNotFound(filePath: String): String = s"$filePath: No such file"
  def fileFormatNotValid(filePath: String): String = s"$filePath: Format not valid"

  def main(args: Array[String]): Unit = {
    args.size == 1 match {
      case false => print(usage)
      case true => main(args.head)
    }
  }

  def main(filePath: String): Unit = {
    val output = try {
      val input = InputReader.fromFile(filePath)
      val colors = BruteForce.solve(input)
      val output = Output.fromColors(colors)

      output.text
    } catch {
      case FileNotFoundException => fileNotFound(filePath)
      case FileFormatNotValidException => fileFormatNotValid(filePath)
    }

    print(output)
  }
}
