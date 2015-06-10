package com.example.actors

import akka.actor.Actor
import akka.actor.Props
import com.example.actors.Messages.Telephone

import scala.util.Random

class Student extends Actor {

  val sneakFactor = Student.generateSneakFactor

  def receive = {
    case Telephone(msg:String) => {
      println(self.path.name + "  "+sneakFactor +" got message " + msg)
      val next = context.actorSelection("../"+getNextLetter())
      val newMsg = if (sneakFactor > 20) Student.alterMessage(msg) else msg
      println(self.path.name + " sending message" + newMsg)
      next ! Telephone(newMsg)
    }
  }

  private def getNextLetter():String = {
    if (self.path.name == "Z") "A"
    else {
      val t = self.path.name.charAt(0) + 1
      ((self.path.name.charAt(0).toInt + 1).toChar + "")
    }
  }


}

object Student {
  val generator = scala.util.Random

  def generateSneakFactor = {
    generator.nextInt(100)
  }

  def generateRandomLetter:String = {
    (generator.nextInt(25) + 65).toChar + ""
  }
  private def alterMessage(msg:String):String = {
    if (msg.contains(generateRandomLetter)) {
      msg.reverse
    }
    else {
      msg.replace(generateRandomLetter, generateRandomLetter)
    }

  }

}
