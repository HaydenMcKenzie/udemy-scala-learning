package playground.oop

object Inheritance extends App {

  // Single class inheritance
  class Animal {
    val creatureType = "wild"
    def eat = println("nom nom")
    private def sniff = println("sniff sniff") // ONLY THIS CLASS
    protected def walk = println("walk walk") // ONLY THIS CLASS AND SUBCLASSES
  }

  class Cat extends Animal { // Cat is subclass and Animal is superclass
    def move = {
      walk
      println("tap tap")
    }
  }

  val cat = new Cat
  cat.eat
  // cat.sniff doesnt work
  cat.move

  // Constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // Overriding
  class Dog extends Animal {
    override val creatureType: String = "Domestic"
    override def walk: Unit = println("kick kick")

    override def eat = { // allows you to override and still call the original value
      super.eat
      println("crunch crunch")
    }
  }
  val dog = new Dog
  dog.walk
  println(dog.creatureType)

  /**
   you can do:
   * class Dog(override val creatureType: String) extends Animal
   this will get you the same result
   Also the class above is the same as doing:
   * class Dog(dogType: String) - and calling it
   * val dog = new Dog("K9")
   They are the same thing
  */

  // type substitution - polymorphism
  val unknownAnimal: Animal = new Dog
  unknownAnimal.eat

  // overRIDING supplying a different implementation in derived classes
  // overLOADING supplying multiple methods with different signatures with the same name and same class

  // Super: Line 36

  // preventing overrides
  // 1 - use Final method
  // 2 - user Final Class
  // 3 - seal the class = extends classes in THIS FILE but not in others 
}
