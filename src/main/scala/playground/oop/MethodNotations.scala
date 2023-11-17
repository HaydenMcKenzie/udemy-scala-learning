package playground.oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favMovie: String) {
    //methods
    def likes(movie: String): Boolean = movie == favMovie
    def hangOutWith(person: Person) = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name, What the heck?"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favMovie"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent
  // Infix notation = operation notation. Only works with one parameter

  // "Operators" in scala
  val tom = new Person("Tom", "Fight Club")

  println(mary hangOutWith tom) // hangOutWith is acting like an operator
  println(mary.hangOutWith(tom)) // What is actually looks like
  println(1 + 2) // Math is doing the same thing
  println(1.+(2)) // This what 1 + 2 actually looks like

  // ALL OPERATORS ARE METHODS
  // Akka actors have ? !

  // Prefix notation
  val x = -1
  val y = 1.unary_- // equivalent with x
  // unary_ prefix only works with - + ~ !

  println(!mary)
  println(mary.unary_!)

  // postfix notion
  println(mary.isAlive)
  println(mary isAlive)

  // Apply
  println(mary.apply())
  println(mary()) // equivalent
}