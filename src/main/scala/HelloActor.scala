import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.io.StdIn

class HelloActor extends Actor {
  // 接收消息并处理
  override def receive: Receive = {
    case "吃了吗?" => println("吃过了")
    case "吃的啥" => println("北京烤鸭")
    case "拜拜" => {
      // 关闭自己
      context.stop(self)
      // 关闭ActorSystem
      context.system.terminate()
    }
  }
}

object HelloActor {

  // 通过ActorSystem创建线程池对象ActorFactory
  private val actorFactory: ActorSystem = ActorSystem("ActorFactory")
  // 通过ActorFactory.actorOf来创建一个Actor，该方法需要两个参数，第一个参数传递一个自定义的Actor类，第二个参数传递一个ActorName
  private val helloActorRef: ActorRef = actorFactory.actorOf(Props[HelloActor], "helloActor")

  def main(args: Array[String]): Unit = {
    var flag = true
    while (flag) {
      print("请输入发送的消息: ")
      val consoleLine: String = StdIn.readLine()
      // !号的作用是:发送消息, 将从控制台接受到的消息发送出去
      helloActorRef ! consoleLine
      if (consoleLine.equals("拜拜")) {
        flag = false
        println("程序即将结束!")
      }
      Thread.sleep(100)
    }
  }

}
