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
}

 object Empty extends MyList[Nothing] {
   def head: Nothing = throw new NoSuchElementException()
   def tail: MyList[Nothing] = throw new NoSuchElementException()
   def isEmpty: Boolean = true
   def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

   def printElements: String = ""
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
}