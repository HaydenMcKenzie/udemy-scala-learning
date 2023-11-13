package playground.basics

object ValuesVariablesAndTypes extends App {

  val x: Int = 43
  println(s"$x") // VALs ARE IMMUTABLE

  // val x = 43
  // Compiler can infer types

  val aString: String = "Hello World"
  println(s"$aString")

  val aBoolean: Boolean = true
  val aChar: Char = 'a'
  val anInt: Int = 1
  val aShort: Short = 6786 // 4 charaters only
  val aLong: Long = 327398654654554L // 8 characters max but you can add L to the end to make it longer
  val aFloat: Float = 3.14f // needs to have an f at the end to make it a float
  val aDouble: Double = 3.14 // All decimal number are automatically a double

  // variables
  var aVariable: Int = 5
  aVariable = 6

}