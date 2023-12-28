package playground.functional

import math.Fractional.Implicits.infixFractionalOps
import math.Integral.Implicits.infixIntegralOps
import math.Numeric.Implicits.infixNumericOps

object TuplesAndMaps extends App {

  // tuples - finite ordered "lists"
  val aTuple = new Tuple2(2, "Hello Scala") //Tuple2[Int,String] = (Int, String)

  println(aTuple._1)
  println(aTuple.copy(_2 = "Goodbye Java"))
  println(aTuple.swap) // ("Hello Scala", "2")

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue("No Such Name")
  println(phoneBook)

  // map ops
  println(phoneBook.contains("Jim")) // true
  println(phoneBook("Jim")) // 555
  println(phoneBook("Mary")) // Will Crash but with the .withDefaultValue(x), it wont

  // add a pairing
  val newParing = "Mary" -> 678
  val newPhoneBook = phoneBook + newParing
  println(newPhoneBook)

  // functionals on maps
  // map, flatmap and filter
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))
  // FilterKeys
  println(phoneBook.view.filterKeys(_.startsWith("J")).toMap) // can use x => x.startWith("J")
  // MapValues
  println(phoneBook.view.mapValues(number => "0245-" + number).toMap)
  // Use FilterKeys and MapValues for Maps

  // conversions to other collections
  println(phoneBook.toList)
  println(List(("Daniel", 555)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(names => names.charAt(0)))
}