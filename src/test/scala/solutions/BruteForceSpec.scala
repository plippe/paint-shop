package com.github.plippe.paintshop.solutions

import org.scalatest.FunSuite

import com.github.plippe.paintshop.color._
import com.github.plippe.paintshop.customer._

class BruteForceSpec extends FunSuite {

  val gloss1 = Color(1, ColorFinish.Gloss)
  val gloss2 = Color(2, ColorFinish.Gloss)
  val gloss3 = Color(3, ColorFinish.Gloss)

  val matte1 = Color(1, ColorFinish.Matte)
  val matte2 = Color(2, ColorFinish.Matte)
  val matte3 = Color(3, ColorFinish.Matte)

  test("BruteForce.colorPermutations should return an empty list for no color") {
    assert(BruteForce.colorPermutations(0) === Seq(Seq.empty))
  }

  test("BruteForce.colorPermutations should return permutations of finish") {
    val permutations = BruteForce.colorPermutations(1).toSeq

    assert(permutations.size === 2)
    assert(permutations.contains(Seq(gloss1)))
    assert(permutations.contains(Seq(matte1)))
  }

  test("BruteForce.colorFinishPermutations should return all permutations") {
    val permutations = BruteForce.colorPermutations(3).toSeq

    assert(permutations.size === 8)
    assert(permutations.contains(Seq(gloss3, gloss2, gloss1)))
    assert(permutations.contains(Seq(gloss3, gloss2, matte1)))
    assert(permutations.contains(Seq(gloss3, matte2, gloss1)))
    assert(permutations.contains(Seq(matte3, gloss2, gloss1)))
    assert(permutations.contains(Seq(gloss3, matte2, matte1)))
    assert(permutations.contains(Seq(matte3, gloss2, matte1)))
    assert(permutations.contains(Seq(matte3, matte2, gloss1)))
    assert(permutations.contains(Seq(matte3, matte2, matte1)))
  }

  test("BruteForce.validColorPermutations should return empty list if no permutations are valid for all orders") {
    val permutations = BruteForce.colorPermutations(3)
    val customerOrders = Seq(
      CustomerOrder(Customer(1), Seq(gloss1)),
      CustomerOrder(Customer(2), Seq(matte1))
    )

    val validPermutations = BruteForce.validColorPermutations(customerOrders, permutations)
    assert(validPermutations === Seq.empty)
  }

  test("BruteForce.validColorPermutations should return color permutations that are valid for all orders") {
    val permutations = BruteForce.colorPermutations(3)
    val customerOrders = Seq(
      CustomerOrder(Customer(1), Seq(gloss1)),
      CustomerOrder(Customer(2), Seq(gloss2))
    )

    val validPermutations = BruteForce.validColorPermutations(customerOrders, permutations).toSeq
    assert(validPermutations.size === 2)
    assert(validPermutations.contains(Seq(gloss3, gloss2, gloss1)))
    assert(validPermutations.contains(Seq(matte3, gloss2, gloss1)))
  }

  test("BruteForce.cheapest should return empty list if no permutations are passed") {
    assert(BruteForce.cheapest(Seq.empty) === Seq.empty)
  }

  test("BruteForce.cheapest should return the permutation with lowest price") {
    val permutations = BruteForce.colorPermutations(3)
    assert(BruteForce.cheapest(permutations) === Seq(gloss3, gloss2, gloss1))
  }
}
