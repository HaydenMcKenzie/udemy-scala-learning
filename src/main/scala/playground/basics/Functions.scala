package playground.basics

object Functions extends App {
  def aFunction(a: String, b: Int) = {
    a + " " + b
  }

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }

  println(aRepeatedFunction("Hello\n", 5))

  def aFunctionWithSideEffects(aString: String): Unit = {println(s"$aString")}

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n-1) // n = 10. Solution 10 + 9
  }

  // E1: Greeting Function (Name, Age) return "Hi, my name is $name and I am $age years old"
  def greetingFunction(name: String, age: Int): Unit = {
    println(s"Hi, my name is $name and I am $age years old")
  }

  // E2: Factorial Function 1 * 2 * 3 ... n
  def factorial(n: Int): Int = {
    if (n <= 0) 1
    else n * factorial(n-1)
  }

  // E3: Fibonacci Function
  def fibonacci(n: Int): Int = {
    if (n <= 2) 1
    else fibonacci(n-1) + fibonacci(n-2)
  }

  println(fibonacci(8))

  // E4: is Prime number
  def isPrime(n: Int): Boolean = {
    def isPrimeUtil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUtil(t-1)

    isPrimeUtil(n / 2)
  }
  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 17))
}