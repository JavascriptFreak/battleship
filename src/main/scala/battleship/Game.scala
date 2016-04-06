package battleship

import Direction.Direction
import battleship.CellType.CellType

/** Class description
  *
  */
class Game( private val size: Int ) {

  val ships: List[Int] = List(2, 2, 2, 2, 3, 3, 3, 4, 4, 5)

  var players: List[Player] = List(
    new Player(0, Battlefield(size)),
    new Player(1, Battlefield(size))
  )

  private def nextPlayer( id: Int ): Int = (id + 1) % players.size

  private def updatePlayer( id: Int, x: Int, y: Int, t: CellType ): Unit = players = players.updated(id, new Player(id, players(id).battlefield.update(x, y, t)))

  def placeShip( length: Int, id: Int, x: Int, y: Int, direction: Direction ): Unit = {

    if(length != 0) {

      if(players(id).battlefield.cellAt(x, y) == CellType.Ship) throw new IllegalArgumentException("You already have placed ship here!")

      if(x > size || y > size || x < 0 || y < 0) throw new IllegalArgumentException("You cant place your ship outside the field")

      updatePlayer(id, x, y, CellType.Ship)

      if(direction == Direction.Horizontal) placeShip(length - 1, id, x + 1, y, direction)

      else placeShip(length - 1, id, x, y + 1, direction)

    }

  }

  def guessPosition( player: Int, x: Int, y: Int ): Unit = {

    val c: CellType = players(player).battlefield.cellAt(x, y)

    if(c == CellType.Ship) updatePlayer(player, x, y, CellType.Hit)

    else if(c == CellType.Water) updatePlayer(player, x, y, CellType.Miss)

    else throw new Error("You can't guess already guessed position")

  }

  def checkWinner( player: Int ): Boolean = {

    for {
      x <- 0 until 10
      y <- 0 until 10
    } yield if(players(player).battlefield.cellAt(x, y) == CellType.Ship) return false

    true

  }

}
