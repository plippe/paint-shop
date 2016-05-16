package com.github.plippe.paintshop.io

trait OutputWriter {

  def print(toOutput: Any): Unit = {
    println(toOutput)
  }

}
