package com.zus.pieces

import kotlin.math.abs

data class Position(val file: Char,
                    val rank: Int) {
    override fun toString(): String {
        return "$file$rank"
    }
}

sealed class Piece(var position: Position) {
    abstract fun canCapture(opponent: Piece): Boolean
}

class Bishop(position: Position) : Piece(position) {
    override fun canCapture(opponent: Piece): Boolean {
        val fileDistance = abs(position.file.code - opponent.position.file.code)
        val rankDistance = abs(position.rank - opponent.position.rank)
        return fileDistance == rankDistance && fileDistance != 0
    }
}

class Rook(position: Position) : Piece(position) {
    override fun canCapture(opponent: Piece): Boolean {
        val sameFile = position.file == opponent.position.file
        val sameRank = position.rank == opponent.position.rank
        return (sameFile || sameRank) && !(sameFile && sameRank)
    }

    fun move(direction: String, steps: Int) {
        var newFile = position.file
        var newRank = position.rank

        when (direction) {
            "up" -> {
                val totalRank = (position.rank - 1 + steps) % 8
                newRank = totalRank + 1
            }
            "right" -> {
                val newFileIndex = ((position.file - 'a' + steps) % 8)
                newFile = 'a' + newFileIndex
            }
        }
        position = Position(newFile, newRank)
    }
}
