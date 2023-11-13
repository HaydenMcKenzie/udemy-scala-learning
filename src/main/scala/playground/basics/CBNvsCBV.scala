package playground.basics

import scala.util.Random

object CBNvsCBV extends App {

  var r: Random = new Random()

  def calledByValue(x: Long): Unit = {
    println(s"By value: $x")
    println(s"By value: $x")
  }

  def calledByName(x: => Long): Unit = {
    println(s"By name: $x")
    println(s"By name: $x")
  }

  calledByValue(r.nextInt())
  calledByName(r.nextInt())


  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int): Unit = println(x)

  printFirst(34, infinite())
}
