package playground.functional

object MapFlapMapFilterFor extends App {

  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)

  //map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  //filter
  println(list.filter(_ % 2 == 0))

  //flatmap
  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))

  // test: print all combinations between to lists
  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')
  // List("a1", "a2" ... "d4"

  // change two loops into functional programming - flatmap and map
  val combinations = numbers.flatMap(n => chars.map(c => "" + c + n))
  println(combinations)

  //If we added another list, we would just change the map to a flat map
  val colors = List("Black", "White")
  val combinations2 = numbers.flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
  println(combinations2)
  // above is called "iterating"



  // Foreach
  list.foreach(println)
  //for-comprehensions - makes the combinations2 look more presentable and readable
  val forCombinations = for {
    n <- numbers
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
  println(forCombinations) // more preferred in practice

  // We can add expressions to them aswell
  val forCombinations2 = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
  println(forCombinations2) // equivalent to numbers.filter(_ % 2 == 0).flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))

  //You can rewrite for foreach aswell
  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map { x =>
    x * 2
  }

  /*
   1.  MyList supports for-comprehensions?
       Map(f: A => B) => MyList[B]
       filter(f: A => Boolean) => MyList[A]
       FlatMap(f: A => MyList[B]) => MyList[B]
    YES IT IS COMPATIBLE
   2.  a Small collection of at Most ONE element - Maybe[+T]
        - Map, flatMap, filter
  */
}
