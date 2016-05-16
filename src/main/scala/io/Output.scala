package com.github.plippe.paintshop.io

import com.github.plippe.paintshop.color.Color

case class Output(text: String)

object Output {

  val empty = Output("No solution exists")

  /**
   * Sort and extract color finish out of list of colors
   *
   * @param colors the colors to output
   * @return an output object with the desired text
   */
  def fromColors(colors: Iterable[Color]): Output = {
    colors match {
      case Nil => Output.empty
      case colors =>
        val sortedColors = colors.toSeq.sortBy { _.id }
        val sortedFinish = sortedColors.map { _.finish.name }
        val text = sortedFinish.mkString(" ")

        Output(text)
    }
  }

}
