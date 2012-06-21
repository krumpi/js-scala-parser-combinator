package scala.js.samples

import java.io.{FileWriter, File, PrintWriter}
import scala.js.{JSGenOpt, JSExp, JS}

trait SampleProg { this: JS =>
  /**
   * Simple abs function
   *
   * Note that the expression -n is not supported
   */
  def abs(n: Rep[Int]): Rep[Int] = if (n < 0) -1 * n else n

  /**
   * Transitive abs, calls the previously defined abs
   *
   * The generated JS code does not call abs but embedds the abs function definition
   */
  def tabs(n: Rep[Int]): Rep[Int] = abs(n)

  /**
   * Simple loop based factorial function
   */
  def factorial(x: Rep[Int]): Rep[Int] = {
    // This code is eliminated by the generator
    var f:Rep[Int] = 1
    for (i <- range(0, x))
      f = f * i
    f
  }

  /**
   * Recursive factorial
   *
   * JS code generation leads to a StackOverflow
   */
  def recursivefactorial(n: Rep[Int]): Rep[Int] = {
    if (n <= 1) 1 else n * recursivefactorial(n - 1)
  }

}

/**
 * Launcher of the test
 */
object Main extends App {
  val resultFile = new File("generated.js")
  val output = new PrintWriter(new FileWriter(resultFile))
  new SampleProg with JSExp {
    self =>
    val codegen = new JSGenOpt {
      val IR: self.type = self
    }
    codegen.emitSource(abs _, "abs", output)
    codegen.emitSource(abs _, "tabs", output)
    codegen.emitSource(factorial _, "factorial", output)
  }
  output.write("print('calculated factorial(12): ' + factorial(12) + ' should be 120');")

  output.close()
}