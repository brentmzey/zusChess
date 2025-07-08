package com.zus

import com.zus.pieces.Bishop
import com.zus.pieces.Rook

class Board(private val bishop: Bishop, private val rook: Rook) {
    fun isRookCaptured(): Boolean {
        return bishop.canCapture(rook)
    }
}