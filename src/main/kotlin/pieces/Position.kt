package com.zus.pieces

data class Position(val file: Char,
                    val rank: Int) {
    override fun toString(): String {
        return "$file$rank"
    }
}