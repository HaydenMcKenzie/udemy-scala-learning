package playground.functional

import java.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  //unsafe APIs
  def unsafeMethod(): String = null
  // WRONG
  //val result = Some(unsafeMethod())
  // RIGHT
  val result = Option(unsafeMethod()) // Some or None
  println(result)

  // chained Methods
  def backupMethod(): String = "A Valid Result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // Functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE - Do not use this

  // map, flatmap and filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))




  // exercise
  val config: Map[String, String] = Map(
    "host" -> "172.21.02.1",
    "port" -> "80"
  )
  class Connection {
    def connect = "Connected"
  }
  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }
  // Try to establish a connection, if so print the connect method
  val host = config.get("host")
  val port = config.get("port")
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h,p)))
  /* In other words, this is what it is doing:
    if (h != null)
      if (p != null)
        return Connection.apply(h,p)
    return Null
  */

  val connectionStatus = connection.map(c => c.connect)
  /* In other words, this is what it is doing:
      if (c != null)
        return c.connect
      return Null
    */

  // if ConnectionStatus == null println None else print Some(connectionstatus.get)
  println(connectionStatus)


  connectionStatus.foreach(println)
  /* In other words, this is what it is doing:
    if (status != null)
      println(status)
    return Null
  */

  // chained calls
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)
}
