package com.example

import akka.actor.{Actor, Props, ActorSystem}

class TestActor extends Actor {
  def receive = {
    case "Good Morning" => println("Good Morning Sire!")
    case "Good Night" => println("Good Night to you as well")
  }
}
/**
 */
object Main {


    val system = ActorSystem("BadShakespeare")
    val actor = system.actorOf(Props[TestActor], "Shake")


  def send(msg:String ): Unit = {
    println(s"Me: $msg")
    actor ! msg
    Thread.sleep(100)
  }

  def main(args: Array[String]): Unit = {
    send("Good Morning")
    send("Good Night")
    send("Unknown")
    system.shutdown()
  }

}
