package playground.functional

object AnonymousFunctions extends App {

  /**
    val doubler = new Function1[Int, Int] {
      override def apply(x: Int) = x * 2
    }
   */
  // IS THE SAME AS
  val doubler: Int => Int = x => x * 2 // anonymous function or LAMBDA

  // Multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // No params
  val justDoSomething: () => Int = () => 3
  println(justDoSomething)   // Returns : playground.functional.AnonymousFunctions$$$Lambda$16/0x00000008000c1040@e720b71
  println(justDoSomething()) // Returns : 3
  // With Lambda's you need the ()

  // Used LAMBDAs with {}
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MORE SYNTAX SUGAR
  // val niceIncrementer: Int => Int = (x: Int) => x + 1
  val niceIncrementer: Int => Int = _ + 1 // Equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // Equivalent to (a,b) => a + b

  /*
  1. MyList: Replace all FunctionX calls with Lambdas
  2. Rewrite the "special" adder as an anonymous function
  */

  val superAdd = (x: Int) => (y: Int) => x + y
  println(superAdd(3) (4))
}
