package com.zus.actions

data class MoveDirectives
(
    val moveDirection: Direction,
    val fileMoveDistance: Int?,
    val rankMoveDistance: Int?
)
