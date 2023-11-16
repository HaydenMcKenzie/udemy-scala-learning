package playground.basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println("Computing factorial of " + n + " - I first need the factorial of " + (n - 1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)


      result
    }
  //println(factorial(5000))

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accum: BigInt): BigInt = {
      if (x <= 1) accum
      else factHelper(x-1, x*accum)
    }
    factHelper(n,1)
  }

  println(anotherFactorial(5000))

  // E1: concatenate a String
  @tailrec
  def concatenateTailrec(aString: String, n: Int, accum: String): String =
    if (n <= 0) accum
    else concatenateTailrec(aString, n-1, aString + accum)

  //println(concatenateTailrec("Hello\n", 3, ""))

  // E2: isPrime
  def isPrime(n: Int): Boolean = {
    def isPrimeUtil(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeUtil(t-1, n % t != 0 && isStillPrime)

    isPrimeUtil(n / 2, true)
  }
  //println(isPrime(7))
  //println(isPrime(10))

  // E3: Fibonacci
  def fibonacci(n: Int): Int = {
    def fibTailRec(i: Int, last: Int, nextToLast: Int): Int =
      if (i >= n) last
      else fibTailRec(i + 1, last + nextToLast, last)

    if (n <= 2) 1
    else fibTailRec(2, 1, 1)
  }

  //print(fibonacci(8))

}