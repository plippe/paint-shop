package com.github.plippe.paintshop.solutions

import com.github.plippe.paintshop.io.Input
import com.github.plippe.paintshop.customer._
import com.github.plippe.paintshop.color._

object BruteForce {
  def solve(input: Input): Iterable[Color] = {
    val colorPermutations = BruteForce.colorPermutations(input.colorCount)
    val validColorPermutations = BruteForce.validColorPermutations(input.customerOrders, colorPermutations)
    val cheapestPermutation = BruteForce.cheapest(validColorPermutations)

    cheapestPermutation
  }

  /**
   * Obtain all permutations of Gloss and Matte for a set amount of colors
   *
   * Recursive
   *
   * @param colorCount the amount of colors
   * @param colors the various permutations built before
   * @return all color permutations possible with the given amount of colors
   */
  def colorPermutations(colorCount: Int, colors: Seq[Color] = Seq.empty): Iterable[Iterable[Color]] = {
    colorCount match {
      case n if n <= 0 => Seq(colors)
      case n =>
        colorPermutations(n - 1, colors :+ Color(n, ColorFinish.Gloss)) ++
          colorPermutations(n - 1, colors :+ Color(n, ColorFinish.Matte))
    }
  }

  /**
   * Filter out color permutations that don't please all customers
   *
   * @param customerOrders the orders that must be fulfilled
   * @param colorPermutations the color permutations that will be compared to the orders
   * @return the color permutations that please all customer
   */
  def validColorPermutations(customerOrders: Iterable[CustomerOrder], colorPermutations: Iterable[Iterable[Color]]): Iterable[Iterable[Color]] = {
    colorPermutations.filter { colorPermutation =>
      customerOrders.forall(_.acceptableColors(colorPermutation))
    }
  }

  /**
   * Obtain the cheapest color permutation out of list
   *
   * @param colorPermutations the color permutations that will be compared to each other
   * @return the first color permutation with the lowest price
   */
  def cheapest(colorPermutations: Iterable[Iterable[Color]]): Iterable[Color] = {
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
