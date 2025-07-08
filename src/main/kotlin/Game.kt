package com.zus

import com.zus.actions.Direction
import com.zus.pieces.Bishop
import com.zus.pieces.Position
import com.zus.pieces.Rook
import org.slf4j.LoggerFactory
import kotlin.random.Random

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
            val moveDirection = if (coinToss == "Heads") Direction.UP else Direction.RIGHT

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
