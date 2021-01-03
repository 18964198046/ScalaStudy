package work.answer4

import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, Props}


class AActor(name: String) extends Actor {
  var count = 0;
  var ref: ActorSelection = _
  override def preStart(): Unit = {
    ref = context.actorSelection("akka://ActorFactory/user/BActor")
  }
  override def receive: Receive = {
    case _ => {
      Thread.sleep(1000)
      val word = s"AActor($name): 挺猛，看我降龙十八掌...第${count + 1}掌"
      println(word)
      count += 1
      ref ! word
    }
  }
}

class BActor(name: String) extends Actor {
  var count = 0
  var ref: ActorSelection = _
  override def preStart(): Unit = {
    ref = context.actorSelection("akka://ActorFactory/user/AActor")
  }
  override def receive: Receive = {
    case _ => {
      Thread.sleep(1000)
      val word = s"BActor($name): 厉害！佛山无影脚 第${count + 1}脚";
      println(word)
      count += 1
      ref ! word
    }
  }
}

object ActorCommunication {

  private val actionFactory: ActorSystem = ActorSystem("ActorFactory")
  private val aActorRef: ActorRef = actionFactory.actorOf(Props(new AActor("乔峰")), "AActor")
  private val bActorRef: ActorRef = actionFactory.actorOf(Props(new BActor("黄飞鸿")), "BActor")

  def main(args: Array[String]): Unit = {
    println("AActor 出招了...")
    println("Start, OK!")
    println("我打")
    aActorRef ! ""
  }

}
