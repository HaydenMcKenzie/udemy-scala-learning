package playground.oop

object AbstractDataTypes extends App {

  // abstract
  abstract class Animal {
    val creatureType: String = "wild"
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    def eat: Unit = println("crunch crunch")
  }

  // Traits
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "free meat"
  }
  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    def eat: Unit = println("nomnomnom")
    def eat(animal: Animal): Unit = println(s"I am a croc and I am eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract classes
  // 1 - traits don't have constructor parameters before Scala 3
  // 2 - multiple traits but one extends
  // 3 - traits are behaviour
}
