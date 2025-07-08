package com.zus

import com.zus.actions.Direction
import com.zus.pieces.Bishop
import com.zus.pieces.Position
import com.zus.pieces.Rook
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ChessLogicTest {

    @Test
    fun `rook wraps around board horizontally`() {
        val rook = Rook(Position('h', 1))
        rook.move(Direction.RIGHT, 1)
        assertEquals(Position('a', 1), rook.position)
    }

    @Test
    fun `rook wraps around board vertically`() {
        val rook = Rook(Position('a', 8))
        rook.move(Direction.UP, 1)
        assertEquals(Position('a', 1), rook.position)
    }

    @Test
    fun `bishop can capture rook on diagonal`() {
        val bishop = Bishop(Position('c', 3))
        val rook = Rook(Position('e', 5))
        assertTrue(bishop.canCapture(rook))
    }

    @Test
    fun `bishop cannot capture rook not on diagonal`() {
        val bishop = Bishop(Position('c', 3))
        val rook = Rook(Position('c', 5))
        assertFalse(bishop.canCapture(rook))
    }

    @Test
    fun `rook can capture bishop on same file`() {
        val bishop = Bishop(Position('c', 3))
        val rook = Rook(Position('c', 7))
        assertTrue(rook.canCapture(bishop))
    }

    @Test
    fun `rook cannot capture bishop not on same file or rank`() {
        val bishop = Bishop(Position('c', 3))
        val rook = Rook(Position('d', 5))
        assertFalse(rook.canCapture(bishop))
    }

}