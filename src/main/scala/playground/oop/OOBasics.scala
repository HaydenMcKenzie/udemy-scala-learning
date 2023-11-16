package playground.oop

object OOBasics extends App{

  val person = new Person("John", 26)
  // println(person) returns playground.oop.Person@3aeaafa6 -> memory location

  // For example, age is a class parameter but not a member -> field is the actual name
  // To make it a parameter and not a field, we use val/var in the function name
  println(person.age)
  println(person.x) // can print stuff from body of classes. However everything in the class will be evaluated. Meaning it will return 3 and 2

  // Calling Methods
  person.anotherGreet("Daniel") // prints "Daniel says: Hi Daniel" but we need it to say "John says: Hi Daniel"
  person.greet("Daniel")// You use ${this.name} to make it John
  person.greet() // returns John. If the parameter has the same name as the class, the new name will overwrite
}

// constructor
class Person(name: String, val age: Int) {
  // body defines the implementation of this class. Same as code blocks
  val x = 2
  println(1 + 2)

  //method
  def anotherGreet(name: String): Unit = println(s"$name says: Hi $name")
  def greet(name: String): Unit = println(s"${this.name} says: Hi $name")
  def greet(): Unit = println(s"HI, I am $name") // overloading
  // def greet(): Int = 42 will confuse the compiler

  // multiple constructors
  def this(name: String) = this(name, 0) // Auxiliary constructor. Can only call another constructor
  // They are a bit inefficient as you can just add default args to the class 
}