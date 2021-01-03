package work.answer2

import scala.io.StdIn

class Player(name:String, score:Int)
class User(name:String, score:Int) extends Player(name, score)
class Computer(name:String, score: Int) extends Player(name, score)

object FingerGuessGame {

  def main(args: Array[String]): Unit = {
    println("-----------------欢迎进入游戏世界-----------------")
    println("***********************************************")
    println("********************猜拳,开始********************")
    println()
    println("请选择对战角色:(1.刘备 2.关羽 3.张飞)")
    var role = StdIn.readLine()
    role match {
      case role == "1" => println("你选择了与")
    }
    println("请出拳:(1.剪刀 2.石头 3.布)")

  }

}
