package playground.oop

import lectures.part2oop.Generics.MyList

abstract class MyList {
  /**
   * head = first element of list
   * tail = remainder of the list
   * isEmpty = boolean
   * add = recieves int and returns a new list => new list
   * toString => a rep of the list
   */

  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(element: Int): MyList

  def printElements: String
  //polymorphic 
  override def toString: String = "[" + printElements + "]"
}

 object Empty extends MyList {
   def head: Int = throw new NoSuchElementException()
   def tail: MyList = throw new NoSuchElementException()
   def isEmpty: Boolean = true
   def add(element: Int): MyList = new Cons(element, Empty)

   def printElements: String = ""
 }

class Cons(h: Int, t: MyList) extends MyList {
  def head: Int = h
  def tail: MyList = t
  def isEmpty: Boolean = false
  def add(element: Int): MyList = new Cons(element, this)

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
}