package com.github.plippe.paintshop.solutions

import com.github.plippe.paintshop.io.Input
import com.github.plippe.paintshop.customer._
import com.github.plippe.paintshop.color._

object BruteForce {
  def solve(input: Input): Iterable[Color] = {
    val colorPermutations = BruteForce.colorPermutations(input.colorCount)
    val validColorPermutations = BruteForce.validColorPermutations(input.customerOrders, colorPermutations)
    val bestPermutation = BruteForce.best(validColorPermutations)

    bestPermutation
  }

  def colorPermutations(colorCount: Int, colors: Seq[Color] = Seq.empty): Iterable[Iterable[Color]] = {
    colorCount match {
      case n if n <= 0 => Seq(colors)
      case n =>
        colorPermutations(n - 1, colors :+ Color(n, ColorFinish.Gloss)) ++
          colorPermutations(n - 1, colors :+ Color(n, ColorFinish.Matte))
    }
  }

  def validColorPermutations(customerOrders: Iterable[CustomerOrder], colorPermutations: Iterable[Iterable[Color]]): Iterable[Iterable[Color]] = {
    colorPermutations.filter { colorPermutation =>
      customerOrders.forall(_.acceptableColors(colorPermutation))
    }
  }

  def best(colorPermutations: Iterable[Iterable[Color]]): Iterable[Color] = {
    colorPermutations match {
      case Nil => Seq.empty
      case colorPermutations =>
        val colorPermutationsSortedByPrice = colorPermutations.toSeq.sortBy { colors =>
          colors.foldLeft(0) { case (sum, color) => sum + color.price }
        }

        colorPermutationsSortedByPrice.head
    }
  }
}
