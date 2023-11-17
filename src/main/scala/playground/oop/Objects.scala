package playground.oop

import scala.language.postfixOps

object Objects extends App {

  // SCALA DOESN'T NOT HAVE CLASS LEVEL FUNCTIONALITY - "STATIC"
  object Person { // type + only instance
    // "Static"/"class" - level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // Factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobby")
  }
  /** Separate instance level functionality from "static" */
  class Person(val name: String) {
    // instance-level functionality
  }
  /**
  COMPANIONS
    ALL the code that we are EVER GOING TO ACCESS is will be accessed from some kind of Instance - regular or singleton
    Scala is more object oriented than most object oriented languages INCLUDING Java
  */

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = SINGLETON INSTANCE
  val mary = Person // object
  val john = Person // object
  println(mary == john) // returns true as it is calling the same instance

  val nat = new Person("Nat") // class
  val nick = new Person("Nick") // class
  println(nat == nick) // returns false as it is calling different instances

  val bobby = Person(nat, nick)

  // Scala Applications = Scala objects with
  // def main(args: Array[String]): Unit
}
