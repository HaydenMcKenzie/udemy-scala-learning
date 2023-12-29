package playground.functional

import scala.util.*

object ErrorHandling extends App {

  /**
   try {
      val config: Map[String, String] = loadConfig(path)
   } catch {
      case _: IOException => // handle IOException
      case _: Exception =>   // handle Other expressions
   }

   A Try is a wrapper for a computation that might fail or not

   sealed abstract class Try[+T]
   case class Failure[+T](t: Throwable) extends Try[T]
   case class Success[+T](value: T) extends Try[T]
   */

  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU BUSTER")
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // Syntax sugar
  val anotherPotentialFailure = Try {
    // code here
  }

  // utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  // IF you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterFallBack = betterUnsafeMethod() orElse betterBackupMethod()

  // Also has map, flatmap and filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.filter(_ > 10))
  println(aSuccess.flatMap(x => Success(x * 10)))

  // EXERCISE
  val host = "losthost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...<html>"
      else throw new RuntimeException("Connection Interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }
  object HttpService {
    val random = new Random(System.nanoTime())
    def getConnection(host: String, port: String): Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  // If you get the html page from the connection, print it ot the console
  val possibleConnection = HttpService.getSafeConnection(host, port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHTML.foreach(renderHTML)

  // short hand - chain calls
  HttpService.getSafeConnection("host", "port")
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(println)

  // for-comprehensions
  for {
    connection <- HttpService.getSafeConnection(host, port)
    html <- connection.getSafe("/home")
  } renderHTML(html)
}
