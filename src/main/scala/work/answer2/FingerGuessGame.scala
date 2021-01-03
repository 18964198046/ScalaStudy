package work.answer2

import scala.io.StdIn
import scala.util.Random

class Player(name:String, score:Int)
class User(name:String, score:Int) extends Player(name, score)
class Computer(var name:String, score: Int) extends Player(name, score)

object FingerGuessGame {

  def main(args: Array[String]): Unit = {
    println("-----------------欢迎进入游戏世界-----------------")
    println("***********************************************")
    println("********************猜拳,开始********************")
    println()

    var computer:Computer = null
    while (computer == null) {
      println("请选择对战角色:(1.刘备 2.关羽 3.张飞) ")
      StdIn.readLine() match {
        case "1" => computer = new Computer("刘备", 0)
        case "2" => computer = new Computer("关羽", 0)
        case "3" => computer = new Computer("张飞", 0)
        case _ => println("输入不符合规范, 请重新选择对战角色!")
      }
    }
    println(s"你选择了与${computer.name}对战")
    println("要开始了吗? y/n")

    var count = 0;
    var userScore = 0;
    var computerScore = 0;
    var drew = 0;
    var userVictory = 0;
    var userFailure = 0;
    var computerVictory = 0;
    var computerFailure = 0;
    if (StdIn.readLine().equals("y")) {
      var flag = true
      while (flag) {
        println("请出拳:(1.剪刀 2.石头 3.布)")
        val userGesture = StdIn.readLine() match {
          case "1" => println("你出拳:剪刀"); "剪刀"
          case "2" => println("你出拳:石头"); "石头"
          case "3" => println("你出拳:布"); "布"
          case _ => println("输入不符合规范, 默认出布"); "布"
        }

        println(s"${computer.name}出拳!")
        val computerGesture = Random.nextInt(3) match {
          case 0 => "剪刀"
          case 1 => "石头"
          case 2 => "布"
        }
        println(s"${computer.name}出拳:${computerGesture}")

        if (userGesture.equals(computerGesture)) {
          drew += 1
          userScore += 1
          computerScore += 1
          println("结果: 和局! 下次继续努力!")
        } else {
          if ((userGesture.equals("剪刀") && computerGesture.equals("布"))
            || (userGesture.equals("石头") && computerGesture.equals("剪刀"))
            || (userGesture.equals("布") && computerGesture.equals("石头"))) {
            userVictory += 1
            computerFailure += 1
            userScore += 2
            println("结果: 恭喜,你赢啦!")
          } else {
            userFailure+= 1
            computerVictory += 1
            computerScore += 2
            println("结果: 悲剧,你输了!")
          }
        }
        count += 1
        println("是否开始下一轮 (y/n)")
        StdIn.readLine() match {
          case "y" => flag = true
          case "n" => flag = false
          case _ => flag = false
        }
      }
      println("退出游戏!")
      println("---------------------------------------------")
      println(s"${computer.name} VS 游客")
      println(s"对战次数${count}次")

      println("姓名  得分  胜局  和局  负局")
      println(s"游客  $userScore  $userVictory  $drew  $userFailure")
      println(s"${computer.name}  $computerScore  $computerVictory  $drew  $computerFailure")
    } else {
      println("退出游戏!")
    }
  }

}
