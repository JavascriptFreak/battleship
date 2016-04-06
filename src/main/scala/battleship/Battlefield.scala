package battleship

import CellType.CellType

/** Class description
  *
  */
object Battlefield {

  def apply( size: Int ) = new Battlefield(List.fill(size)(List.fill(size)(CellType.Water)))

}

class Battlefield( private val content: List[List[CellType]] ) {

  def update( x: Int, y: Int, c: CellType ): Battlefield = new Battlefield(content.updated(x, content(x).updated(y, c)))

  def cellAt( x: Int, y: Int ): CellType = content(x)(y)

}