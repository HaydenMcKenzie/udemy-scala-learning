package playground.oop

object OOExcersies extends App {

  val writer = new Writer("John", "Doe", 2000)
  val novel = new Novel("Book", 2020, writer)

  println(novel.isWrittenBy(writer))
  println(novel.authorAge())
  val copyNovel = novel.copyNovel(2021)
  println(copyNovel.yearOfRelease)

  val counter = new Counter(0)
  counter.inc.dec.inc(2).dec.print
}

class Writer(firstName: String, surname: String, var year: Int) {
  def fullName(): String = s"$firstName $surname"
}

class Novel(name: String, val yearOfRelease: Int, author: Writer) {
  // autherAge at release
  def authorAge(): Int = this.yearOfRelease - author.year

  // isWrittenBy
  def isWrittenBy(author: Writer): Boolean = author == this.author

  // copy (new year of release)
  def copyNovel(newYear: Int): Novel = new Novel(name, newYear, author)
}

class Counter(val count: Int) {
  def inc = {
    println("Incrementing")
    new Counter(count + 1)
  }
  def dec = {
    println("Decrementing")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n-1)
  }
  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n-1)
  }

  def print = println(count)
}