package playground.basics

object StringOps extends App {

  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7,11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.length)

  val aNumberToString = "2"
  val aNumber = aNumberToString.toInt
  println("a" +: aNumberToString :+ "z")
  println(str.reverse)
  println(str.take(2))

  // Scala-specific

  // S-interpolators
  val name = "David"
  val age = 12
  val greeting = s"My name is $name and I am $age years old"
  val anotherGreeting = s"My name is $name and I will be ${age + 1} years old"

  // F-interpolators
  val speed = 1.2f
  val myth = f"$name%s can run at $speed%2.2f"
  println(myth)
}
