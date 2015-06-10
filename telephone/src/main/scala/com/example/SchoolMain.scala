package com.example

import akka.actor.Props
import com.example.actors.{Messages, Teacher}
import scala.concurrent.duration._

/**
 */
object SchoolMain {

  def main(args: Array[String]):Unit = {
    val system = akka.actor.ActorSystem("Telephone")
    val teacher = system.actorOf(Props[Teacher])
    println("App started")

    import scala.concurrent.ExecutionContext.Implicits.global
    system.scheduler.scheduleOnce(500.millis, teacher, Messages.SchoolStart)
    system.scheduler.scheduleOnce(5.seconds) {
      system.shutdown()
    }

  }

}
