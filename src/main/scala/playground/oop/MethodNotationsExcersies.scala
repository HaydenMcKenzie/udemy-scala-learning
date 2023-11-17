package playground.oop

import scala.language.postfixOps

object MethodNotationsExcersies extends App {

  class Person(val name: String, favMovie: String, val age: Int = 0) {
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favMovie)
    def unary_+ : Person = new Person(name, favMovie, age+1)
    def apply(): String = s"Hi, my name is $name and I like $favMovie"
    def apply(n: Int): String = s"$name watched $favMovie $n times"

    def learns(thing: String): String = s"$name is learning $thing"
    def learnsScala = this learns "Scala"
  }

  val mary = new Person("Mary", "Inception")


  /**
    1. Overload the + operator
      return new person with a nickname
  */
  println((mary + "the rockstar")())
  println((mary + "the rockstar").apply())

  /**
    2. Add age to the Person class
      add a unary + operator => new person with age + 1
      +mary => mary with the age incremeter
  */
  println((+mary).age)

  /**
    3.  Adds a "learns" method in the person class => Mary learns scala
        add a learns Scala method, calls learns method with "Scala"
        use it in postfix notation
  */
  println(mary learnsScala)

  /**
    4.  Overload the apply method
        mary.apply(2) => mary watched inception 2 times
   */
  println(mary.apply(2))
  println(mary(2))
}
