package playground.oop


object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // Anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("Hahaha")
  }

  println(funnyAnimal.getClass)

  /**
   * What it pretty much doing is:
   *
   * class AnonymousClasses$$anon$1 extends Animal {
   *  override def eat: Unit = println("Hahaha")
   * }
   * val funnyAnimal: Animal = new AnonymousClasses$$anon$1
   */

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is ${name}, how can I help you?")
  }

  val jim = new Person("Jim") { // Still need to add a parameter
    override def sayHi: Unit = println(s"Hi, my name is Jim, How can I be of service?")
  }
}
