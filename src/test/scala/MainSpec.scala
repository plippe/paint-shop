package com.github.plippe.paintshop

import org.scalatest.{ FunSuite, BeforeAndAfter }

import com.github.plippe.paintshop.io.Output

class MainSpec extends FunSuite with BeforeAndAfter {
  var output: Seq[String] = Seq.empty
  object MockMain extends Main {
    override def print(toOutput: Any): Unit = {
      output = output :+ toOutput.toString
    }
  }

  before {
    output = Seq.empty
  }

  test("Main.main should print usage if no args given") {
    MockMain.main(Array.empty[String])
    assert(output.contains(MockMain.usage))
  }

  test("Main.main should print file not found if not present") {
    val filePath = "/no/file"
    MockMain.main(Array(filePath))
    assert(output.contains(MockMain.fileNotFound(filePath)))
  }

  test("Main.main should print file format not valid if file not correct") {
    val filePath = getClass.getResource("/input-bad.txt").getPath
    MockMain.main(Array(filePath))
    assert(output.contains(MockMain.fileFormatNotValid(filePath)))
  }

  test("Main.main should return the same results as example 1") {
    MockMain.main(getClass.getResource("/input-1.txt").getPath)
    assert(output.contains("G G G G M"))
  }

  test("Main.main should return the same results as example 2") {
    MockMain.main(getClass.getResource("/input-2.txt").getPath)
    assert(output.contains(Output.empty.text))
  }

  test("Main.main should return the same results as example 3") {
    MockMain.main(getClass.getResource("/input-3.txt").getPath)
    assert(output.contains("G M G M G"))
  }

  test("Main.main should return the same results as example 4") {
    MockMain.main(getClass.getResource("/input-4.txt").getPath)
    assert(output.contains("M M"))
  }
}
