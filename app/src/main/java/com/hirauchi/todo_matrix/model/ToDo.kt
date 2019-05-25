package com.hirauchi.todo_matrix.model

data class ToDo(
    val id: Int,
    val content: String,
    val importance: Float,
    val urgency: Float
)