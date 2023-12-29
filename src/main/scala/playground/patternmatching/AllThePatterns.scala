package playground.patternmatching

import exercises.*
import lectures.*


object AllThePatterns extends App {

  // 1  - constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a Number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case AllThePatterns => "A"
  }

  // 2 - match anything
  // 2.1 Wildcards
  val matchAnything = x match {
    case _ => "Wildcard"
  }
  // 2.2 variable
  val matchAVar = x match {
    case something => s"I've found $something"
  }

  // 3 - tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case (1,1) => ""
    case (something, 2) => s"I've found $something"
  }
  val nestedTuple = (1,(2,3))
  val matchAnestedTuple = nestedTuple match {
    case (_, (2, v)) => ""
  }
  // PMs can be Nested

  // 4 - case classes - constructor pattern
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty => ""
    case Cons(head, Cons(subHead, suhTail)) => ""
  }

  // 5 - list patterns
  val standardList = List(1,2,3,42)
  val standardListMatching = standardList match {
    case List(1, _, _, _) => "" // extractor
    case List(1, _*) => "" // Arbitrary Length
    case 1 :: List(_) => ""
    case List(1,2,3) :+ 42 => ""
  }

  // 6 - type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => ""// explicit Type specifier
    case _ => ""
  }

  // 7 - name binding
  val nameBindingMatch = aList match {
    case nonEmpty @ Cons(_,_) => "" // name binding => use the name later(here)
    case Cons(1, rest @ Cons(2, _)) => ""//name binding inside nested patterns
  }

  // 8 - multi-patterns
  //val multipattern = aList match {
 //   case Empty | Cons(0, _) => ""
 // }

  // 9 - if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => ""
  }


  // Question
  val nums = List(1,2,3)
  val numsMTCH = nums match {
    case listOfStrings: List[String] => "Strings"
    case listOfNumbers: List[Int] => "Ints"
    case _ => ""
  }
  println(numsMTCH) // JVM trick question
}
