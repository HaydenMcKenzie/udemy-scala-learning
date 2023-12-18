package playground.functional

object WhatsAFunction extends App {

  // use functions as first class elements
  // Like playing with plain values
  // problem: We come from an OOP world
    // We would usually just make a class and call it \

  val doubler = new myFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }
  println(stringToIntConverter("3") + 4)

  // Scala supports up to 22 parameters
  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function Types - e.g. Function[A, B, R] === (A,B) => R
  // ALL SCALA FUNCTIONS ARE OBJECTS

  // 1. a function which take 2 strings and concatenates them

  def concatenator: ((String, String) => String) = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + b
  }
  println(concatenator("Hello ", "Scala"))

  // 2. define a function which takes an int and returns another function which takes an Int and returns an Int
    // what's the type of this function
    // how to do it?
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
      override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
        override def apply(y: Int): Int = x + y
      }
    }
  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3) (4)) // curried function
}

trait myFunction[A, B] {
  def apply(element: A): B
}