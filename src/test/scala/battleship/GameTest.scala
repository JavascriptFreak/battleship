package battleship

import org.scalatest._

/** Class description
  *
  */
class GameTest extends FlatSpec with Matchers {

  "It" should "horizontally place a small ship" in {

    val g: Game = new Game(10)

    g.placeShip(2, 0, 0, 0, Direction.Horizontal)

    assert(g.players.head.battlefield.cellAt(0, 0) == CellType.Ship)
    assert(g.players.head.battlefield.cellAt(1, 0) == CellType.Ship)
    assert(g.players.head.battlefield.cellAt(2, 0) == CellType.Water)

  }

  "It" should "vertically place a small ship" in {

    val g: Game = new Game(10)

    g.placeShip(2, 0, 0, 0, Direction.Vertical)

    assert(g.players.head.battlefield.cellAt(0, 0) == CellType.Ship)
    assert(g.players.head.battlefield.cellAt(0, 1) == CellType.Ship)
    assert(g.players.head.battlefield.cellAt(0, 2) == CellType.Water)

  }

  "It" should "throw when user tries to place a ship on a cell which is already occupied" in {

    val g: Game = new Game(10)

    g.placeShip(2, 0, 0, 0, Direction.Horizontal)

    intercept[IllegalArgumentException] { g.placeShip(2, 0, 1, 0, Direction.Horizontal) }

    g.placeShip(2, 0, 2, 0, Direction.Horizontal)

  }

}
