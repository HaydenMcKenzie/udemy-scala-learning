package playground.functional

import math.Fractional.Implicits.infixFractionalOps
import math.Integral.Implicits.infixIntegralOps
import math.Numeric.Implicits.infixNumericOps
import scala.annotation.tailrec

object TuplesAndMaps extends App {

  // tuples - finite ordered "lists"
  val aTuple = new Tuple2(2, "Hello Scala") //Tuple2[Int,String] = (Int, String)

  println(aTuple._1)
  println(aTuple.copy(_2 = "Goodbye Java"))
  println(aTuple.swap) // ("Hello Scala", "2")

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue("No Such Name")
  println(phoneBook)

  // map ops
  println(phoneBook.contains("Jim")) // true
  println(phoneBook("Jim")) // 555
  println(phoneBook("Mary")) // Will Crash but with the .withDefaultValue(x), it wont

  // add a pairing
  val newParing = "Mary" -> 678
  val newPhoneBook = phoneBook + newParing
  println(newPhoneBook)

  // functionals on maps
  // map, flatmap and filter
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))
  // FilterKeys
  println(phoneBook.view.filterKeys(_.startsWith("J")).toMap) // can use x => x.startWith("J")
  // MapValues
  println(phoneBook.view.mapValues(number => "0245-" + number).toMap)
  // Use FilterKeys and MapValues for Maps

  // conversions to other collections
  println(phoneBook.toList)
  println(List(("Daniel", 555)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(names => names.charAt(0)))

  /*
    1.  what would happen if - in the toLowerCase - I had two original entire  "Jim",555 and "JIM, 999
    2.  overly simplified social network based on maps
        person = string
        - add people
        - remove
        - friend
        - unfriend
        - stats = number of friends, most friend, how many people no friends, there is social connection
  */
  // q1
  val testPhoneBook = Map(("Jim", 555), "JIM" -> 999).withDefaultValue("No Such Name")
  println(testPhoneBook.map(pair => pair._1.toLowerCase -> pair._2)) // CAREFUL IN MAPPING KEYS
  // q2
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    val friendA = network(a)
    val friendB = network(b)

    network + (a -> (friendA + b)) + (b -> (friendB + a))

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    val friendA = network(a)
    val friendB = network(b)

    network + (a -> (friendA - b)) + (b -> (friendB - a))

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person


  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  // Jim and Bob are friends, Bob and Mary are friends, Jim and Mary are not friends
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
  // Can use network.view.filterKeys(k => network(k).isEmpty).size
    network.count(pair => pair._2.isEmpty)
  // Can use  network.count(_._2.isEmpty)

  println(nPeopleWithNoFriends(testNet))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean =
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b, Set(), network(a) + a)

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))
}