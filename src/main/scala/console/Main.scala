package console

import battleship.Direction.Direction
import battleship.{Direction, Game}

/** Class description
  *
  */
object Main {

  val g: Game = new Game(10)

  def placeShips(): Unit = {

    for( i <- 0 until 2) {

      println("")
      println(s"Player $i, please set positions of your ships:")
      println("")

      g.ships.foreach((s: Int) => {

        println(s"Ship with a size of $s")

        val input: Array[String] = scala.io.StdIn.readLine().split(",")
        val direction: Direction = if(input(2) == "H") Direction.Horizontal else Direction.Vertical

        g.placeShip(s, i, input(0).toInt, input(1).toInt, direction)

      })

    }

  }

  def guessPositions( player: Int ): Unit = {

    val nextPlayer: Int = if(player == 0) 1 else 0

    println(s"Player $player is on turn")

    val input: Array[String] = scala.io.StdIn.readLine().split(",")

    g.guessPosition(nextPlayer, input(0).toInt, input(1).toInt)

    if(g.checkWinner(nextPlayer)) println(s"Player $player wins!")

    else guessPositions(nextPlayer)

  }

  def main(args: Array[String]): Unit = {

    placeShips()
    guessPositions(0)

  }

}
