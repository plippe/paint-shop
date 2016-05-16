package com.github.plippe.paintshop.color

import enumeratum._

sealed abstract class ColorFinish(override val entryName: String, val price: Int) extends EnumEntry { val name = entryName; }

object ColorFinish extends Enum[ColorFinish] {
  case object Gloss extends ColorFinish("G", 0)
  case object Matte extends ColorFinish("M", 1)

  val values = findValues
}
