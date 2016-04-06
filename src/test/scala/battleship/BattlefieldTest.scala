package battleship

import org.scalatest._

/** Class description
  *
  */
class BattlefieldTest extends FlatSpec with Matchers {

  "Each cell" should "contain Water" in {

    val b: Battlefield = Battlefield(10)

    for {
      i <- 0 until 10
      j <- 0 until 10
    } yield assert(b.cellAt(i, j) == CellType.Water)

  }

  "Each cell" should "be updated according to passed condition" in {

    val b: Battlefield = Battlefield(10)

    assert(b.update(0, 0, CellType.Ship).cellAt(0, 0) == CellType.Ship)
    assert(b.update(5, 3, CellType.Miss).cellAt(5, 3) == CellType.Miss)
    assert(b.update(9, 9, CellType.Hit).cellAt(9, 9) == CellType.Hit)
    assert(b.update(6, 6, CellType.Ship).update(6, 6, CellType.Water).cellAt(6, 6) == CellType.Water)
    assert(b.update(3, 3, CellType.Hit).update(4, 5, CellType.Miss).cellAt(3, 3) == CellType.Hit)

  }

}