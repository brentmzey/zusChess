package com.zus.pieces

import com.zus.actions.Direction
import kotlin.math.abs

sealed class Piece(var position: Position) {
    abstract fun canCapture(opponent: Piece): Boolean
}

class Bishop(position: Position) : Piece(position) {
    override fun canCapture(opponent: Piece): Boolean {
        val fileDistance = abs(position.file.code - opponent.position.file.code)
        val rankDistance = abs(position.rank - opponent.position.rank)
        return fileDistance == rankDistance && fileDistance != 0
    }

    fun move(fileDirection: Int,
             rankDirection: Int,
             steps: Int) {
        val newFile = 'a' + ((position.file - 'a' + fileDirection * steps + 8) % 8)
        val newRank = ((position.rank - 1 + rankDirection * steps + 8) % 8) + 1
        position = Position(newFile, newRank)
    }
}

class Rook(position: Position) : Piece(position) {
    override fun canCapture(opponent: Piece): Boolean {
        val sameFile = position.file == opponent.position.file
        val sameRank = position.rank == opponent.position.rank
        return (sameFile || sameRank) && !(sameFile && sameRank)
    }

    fun move(direction: Direction,
             steps: Int) {
        var newFile = position.file
        var newRank = position.rank

        when (direction) {
            Direction.UP -> {
                val totalRank = (position.rank - 1 + steps) % 8
                newRank = totalRank + 1
            }
            Direction.DOWN -> {
                val totalRank = (position.rank - 1 - steps + 8 * steps) % 8
                newRank = totalRank + 1
            }
            Direction.RIGHT -> {
                val newFileIndex = ((position.file - 'a' + steps) % 8)
                newFile = 'a' + newFileIndex
            }
            Direction.LEFT -> {
                val newFileIndex = ((position.file - 'a' - steps + 8 * steps) % 8)
                newFile = 'a' + newFileIndex
            }
        }
        position = Position(newFile, newRank)
    }
}
