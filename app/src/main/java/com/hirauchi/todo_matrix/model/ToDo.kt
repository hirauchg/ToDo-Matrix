package com.hirauchi.todo_matrix.model

import java.io.Serializable

data class ToDo(
    val id: Int,
    val content: String,
    val importance: Int,
    val urgency: Int
):Serializable