package playground.basics

object Expressions extends App {

  val x = 1 + 2
  println(s"$x")

  println(1 + 2 * 3)
  // + - * / & | ^ << >> >>> (right shift with zero extention)

  println(1 == x)
  // == != > >= < <=

  println(!(1 == 4))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // works with -= *= /= ... This are side effects
  println(s"$aVariable")

  // Instructions (DO) vs Expressions (VALUE)

  // IF expressions
  val aCondition = true
  val aConditionValue = if(aCondition) 5 else 3 // This is DIFFERENT as it is an IF EXPRESSION
  println(s"$aConditionValue") // Will always return 5


  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  } // NEVER WRITE THIS AGAIN

  val aWeirdValue = (aVariable = 3) // unit === void
  println(aWeirdValue)

  // Side effects: prinln(), whiles, reassinging var

  //Code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "Hello World" else "Goodbye"
  }

  // E1: Difference between "hello world" and println("helo world")
  var h = "Hello World" // Only gives h the value of "hello world"
  println(h) // Have to call variable to print it
  println("Hello World")  // prints "hello world' to console

  // Answer:
  // The Types are different, one is a String the other is a Unit


  // E2: val someVal = 2 < 3, what is returned
  // answer = true - correct

  // E3: val someValPt2 = if(someVal) 239 else 986 |New line| 42
  // answer = 42 - correct


}