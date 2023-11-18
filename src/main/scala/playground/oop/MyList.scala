package playground.oop

import lectures.part2oop.Generics.MyList

abstract class MyList[+A] {
  /**
   * head = first element of list
   * tail = remainder of the list
   * isEmpty = boolean
   * add = recieves int and returns a new list => new list
   * toString => a rep of the list
   */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]

  def printElements: String
  //polymorphic
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]

  // concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]
}

 object Empty extends MyList[Nothing] {
   def head: Nothing = throw new NoSuchElementException()
   def tail: MyList[Nothing] = throw new NoSuchElementException()
   def isEmpty: Boolean = true
   def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

   def printElements: String = ""

   def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
   def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
   def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

   def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
 }

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def printElements: String = {
    if(t.isEmpty) "" + h
    else s"$h ${t.printElements}"
  }

  /**
   * How filter Works
   * pseudo: [1,2,3].filter(n % 2 == 0)   Note: Testing for even numbers
   * - [2,3].filter(n % 2 == 0)           Note: 1 isnt an even number, so it move on
   * - new Cons(2, [3].filter(n % 2 == 0) Note: 2 is an even number, creates a new Cons
   * - new Cons(2, Empty)                 Note: 3 isnt an even number, returns empty
   * result: prints only 2                Note: Due to 2 being the other even number, it would only print 2
   */
  def filter(predicate: MyPredicate[A]): MyList[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  /**
   * How Map Works
   * pseudo: [1,2,3].map(n * 2)
   * - new Cons(2, [2,3].map(n * 2)
   * - new Cons(2, new Cons(4, [3].map(n * 2))
   * - new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))
   * result: new Cons(2, new Cons(4, new Cons(6, Empty)))
   */
  def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))
  /**
   * pseudo: [1,2] ++ [3,4,5]
   * - new Cons(1, [2] ++ [3,4,5])
   * - new Cons(1, new Cons(2, Empty ++ [3,4,5])
   * - new Cons(1, new Cons(2, [3,4,5])   Note: which is a Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
   * returns 1 2 3 4 5
   */
  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
  /**
   * How flatMap works
   * pseudo: [1,2].flatMap(n => [n, n+1])
   * - [1,2] ++ [2].flatMap(n => [n, n+1])
   * - [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
   * results [1,2,2,3]
   */
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
    transformer.transform(h) ++ t.flatMap(transformer)
  }
}

trait MyPredicate[-T] {
  def test(elem: T): Boolean
}
trait MyTransformer[-A, B] {
  def transform(elem: A): B
}

object ListTest extends App {
  val list = new Cons(1, Empty)
  println(list.head)
  println(list.add(4).head)

  val linkedList = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(linkedList.tail.head)

  println(linkedList.toString)

  // New Lists After changing it to a generic MyList
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfString: MyList[String] = new Cons("a", new Cons("b", new Cons("c", Empty)))

  println(listOfIntegers.toString)
  println(listOfString.toString)

  // New List after adding Map, FlatMap, Filter and ++
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))
  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))
  }).toString)
}