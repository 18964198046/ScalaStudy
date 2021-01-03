package work.answer1

object DrinkAnswer {

  def buyBeer(money: Int): Int = {
    changeBeer(money / 2)
  }

  def changeBeer(bottle: Int): Int = {
    if (bottle == 0) return 0
    bottle + changeBeer(bottle / 3) + changeBeer(bottle / 5)
  }

  def main(args: Array[String]): Unit = {
    println(s"100元可以喝${buyBeer(100)}瓶啤酒")
  }

}
