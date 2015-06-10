package com.example.actors

import akka.actor.Actor
import akka.actor.Props

class Teacher extends Actor {

  import Messages._

  override def preStart()  {
    for(i <- 0 to 25) {
     // println((65+i).toChar)
      context.actorOf(Props[Student], ""+ ((65+i).toChar) )
    }
    context.children.head ! Telephone("THE QUICK BROWN FOX JUMPED OVER THE LAZY DOG")
  }

  def receive = {
    case GreeterMessages.Done => {
      context.stop(self)
    }
    case SchoolStart => {
       println("School is in Session!")
    }
  }
}

object Messages {
  case class Telephone(msg:String)
  case object SchoolStart
}

