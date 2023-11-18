package playground.oop

object Generics extends App {

  class MyList[A] {
    // use type A
  }
  // val listofIntergers = new MyList[Int]
  // val listofStrings = new MyList[String]

  class MyMap[Key, Value] // an example of 2 generic parameters, but you can have as many as needed
  // Works with traits aswell

  // genertic methods
  object MyList { // cant be type parameterised
    def empty[A]: MyList[A] = null
  }
  val emptyListOfIntergers = MyList.empty[Int]


  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  /**
   * Convaiance - Animal = new Cat
   * Invariance - Animal = new Animal
   * Contravariance - Cat = new Animal
   *
   * One is defined
   * the other is a neutral
   * The other is the reverse of the first
   */

  // 1. List[Cat] extends List[Animal] = COVARIANCE
  class CovarianceList[+A]
  val animal: Animal = new Cat
  val animalList: CovarianceList[Animal] = new CovarianceList[Cat] // Would work

  // Can I add any animal ??? - animalList.add(new Dog). Solution is we returns a list of Animals
  // 2. no. INVARIANCE
  class InvariantList[A]
  val invariantList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. hell no. CONTRAVARIANCE
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal] // can "train" a cat, dog etc

  /**
   * if B extends A, should List[B] extend List [A]
   * List[+A]   - yes
   * List[A]    - no - default
   * List[-A]   - hello no
   */

  // bounded types
  // subtype syntax   = <:  or lowerbounded
  // supertype syntax = >:  or upperbounded
  class Cage[A  <: Animal](animal: A)
  val cage = new Cage(new Dog)
  /**
  class Car
  val newCage = new Cage(new Car) // this doesnt work because car isnt in Class Animal
   */

  class MyListCon[+A] {
    def add[B >: A](element: B): MyListCon[B] = ???
  }
  /**
   A = Cat
   B = Dog then it turns to Animal
   */
}
