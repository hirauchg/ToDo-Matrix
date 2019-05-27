package com.hirauchi.todo_matrix.model

data class MatrixData(
    val importance: Int,
    val urgency: Int,
    val toDoList: List<ToDo>
)