package playground.functional

import scala.util.Random

object Sequences extends App {

  // Seq
  val aSeq = Seq(1,3,2,4)
  println(aSeq)
  println(aSeq.reverse)
  println(aSeq(2))
  println(aSeq ++ Seq(5,6,7))
  println(aSeq.sorted)

  //ranges
  val aRange: Seq[Int] = 1 to 3 // can use 'until'
  aRange.foreach(println)

  (1 to 3).foreach(x => println("Hello"))

  // Lists
  /**
   sealed abstract class List[+A]
   case object Nil extends List[Nothing]
   class class ::[A](val hd: A, val tl: List[A]) extends List[A]

   LinearSeq are immutable linked list
   - head, tail, isEmpty methods are fast: 0(1)
   - Most operations are 0(n): Length and reverse

   Sealed - has two subtypes
   - Object Nil (empty)
   - Class ::
   */

  val aList = List(1,2,3)
  val prepended = 42 +: aList :+ 89
  println(prepended)

  val apple5 = List.fill(5)("apple")
  println(apple5)
  println(aList.mkString("-|-"))

  // Arrays
  /**
   final class Array[T]
    extends java.io.Serializable
    with java.lang.Cloneable

   The equivalent of simple Java Arrays
      - Can be manually constructed with predefined lengths
      - can be mutated
      - Are interoperable with Java's T[] Arrays
      - Indexing is fast
   */

  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  println(threeElements) // returns [I@2f0a87b3
  threeElements.foreach(println) // Int = 0, String = null, Boolean = False

  //mutation
  numbers(2) = 0 // equiv to numbers.update(2,0)
  println(numbers.mkString("-|-"))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers
  println(numbersSeq)

  // Vectors
  /**
   final class Vector[+A]

   The default implementation for immutable sequences
      - effectively constant indexed read and write: O(log32(n))
      - fast element addition: append/prepend
      - implemented as a fixed-branched trie - branch factor 32
      - good performance for large sizes

   val noElements = Vector.Empty
   val numbers = noElements :+ 1 :+ 2 :+ 3    Vector(1,2,3)
   val modified = numbers updated (0,7)       Vector(7,2,3)
   */

  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vector vs list
  val maxRuns = 1000
  val maxCap = 1000000

  def getWriteTime(c: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      c.updated(r.nextInt(maxCap), r.nextInt())
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCap).toList
  val numbersVector = (1 to maxCap).toVector

  // List Pros - keep reference to tail
  // List Cons - Updating elements in the middle takes a long time
  println(getWriteTime(numbersList))
  // Vector Pros - Depth of the time is small
  // Vector Cons - Needs to replace the entire 32-element chunk
  println(getWriteTime(numbersVector))
}
