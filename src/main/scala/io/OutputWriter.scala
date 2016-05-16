package com.github.plippe.paintshop.io

/** Mockable trait to test ouput over writing it to a terminal */
trait OutputWriter {

  /**
   * Print to terminal
   *
   * @param toOutput variable that will be converted to a string, and outputed
   */
  def print(toOutput: Any): Unit = {
    println(toOutput)
  }

}
