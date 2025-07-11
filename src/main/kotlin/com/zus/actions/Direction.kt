package com.zus.actions

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    companion object {
        fun fromString(direction: String): Direction? {
            return when (direction.lowercase()) {
                "up" -> UP
                "down" -> DOWN
                "left" -> LEFT
                "right" -> RIGHT
                else -> null
            }
        }
    }
}