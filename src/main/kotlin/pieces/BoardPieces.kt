package com.zus.pieces

import kotlin.random.Random
import org.slf4j.LoggerFactory

data class Position(val file: Char, val rank: Int) {
    override fun toString(): String {
        return "$file$rank"
    }
}

sealed class Piece(var position: Position) {
    abstract fun canCapture(opponent: Piece): Boolean
}

class Bishop(position: Position) : Piece(position) {
    override fun canCapture(opponent: Piece): Boolean {
        val fileDifference = Math.abs(position.file - opponent.position.file)
        val rankDifference = Math.abs(position.rank - opponent.position.rank)
        return fileDifference == rankDifference
    }
}

class Rook(position: Position) : Piece(position) {
    override fun canCapture(opponent: Piece): Boolean {
        return position.file == opponent.position.file || position.rank == opponent.position.rank
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

class Board(private val bishop: Bishop, private val rook: Rook) {
    fun isRookCaptured(): Boolean {
        return bishop.canCapture(rook)
    }
}

class Game {
    private val logger = LoggerFactory.getLogger(Game::class.java)
    private val bishop = Bishop(Position('c', 3))
    private val rook = Rook(Position('f', 6))
    private val board = Board(bishop, rook)

    fun play() {
        logger.info("--- Game Start ---")
        logger.info("Initial Rook Position: ${rook.position}")
        logger.info("Bishop Position: ${bishop.position}")
        logger.info("--------------------")

        for (round in 1..15) {
            val coinToss = if (Random.nextBoolean()) "Heads" else "Tails"
            val dice1 = Random.nextInt(1, 7)
            val dice2 = Random.nextInt(1, 7)
            val diceSum = dice1 + dice2
            val moveDirection = if (coinToss == "Heads") "up" else "right"

            logger.info("Round $round:")
            logger.info("  Coin Toss: $coinToss -> Move $moveDirection")
            logger.info("  Dice Roll: ($dice1, $dice2) -> Move $diceSum squares")

            rook.move(moveDirection, diceSum)
            logger.info("  Rook's New Position: ${rook.position}")

            if (board.isRookCaptured()) {
                logger.info("!! The Bishop has captured the Rook at ${rook.position}. !!")
                logger.info("--- The player with the Bishop wins! ---")
                return
            }
            logger.info("--------------------")
        }

        logger.info("## The Rook has survived all 15 rounds! ##")
        logger.info("--- The player with the Rook wins! ---")
    }
}