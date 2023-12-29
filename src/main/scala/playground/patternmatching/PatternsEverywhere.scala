package playground.patternmatching

import java.security.Principal

object PatternsEverywhere extends App {

  try {
    // Something
  } catch {
    case e: RuntimeException => ""
    case npe: NullPointerException => ""
    case _ => ""
  }
  // Catches are Matches

  val list = List(1,2,3)
  val evenOnes = for {
    x <- list if x % 2 == 0
  } yield 10 * x
  // generators are based on pattern matching
  val tuples = List((1,2), (3,4))
  val filterTuple = for {
    (first, second) <- tuples
  } yield first * second
  // case classes :: Operators, ....

  val tuple = (1,2,3)
  val (a,b,c) = tuple
  println(b)
  // mutiple value definitions based on pattern matching

  val head :: tail = list
  println(head)
  println(tail)


  // partial function
  val mappedList = list.map {
    case v if v % 2 == 0 => s"${v} is Even"
    case 1 => "the one"
    case _ => "something"
  } // partial function literal

  val mappedList2 = list.map { x => x match
    case v if v % 2 == 0 => s"${v} is Even"
    case 1 => "the one"
    case _ => "something"
  } // partial function literal

  println(mappedList)
  println(mappedList2)
}
