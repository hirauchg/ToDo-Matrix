package com.hirauchi.todo_matrix.ui

import com.hirauchi.todo_matrix.fragment.ToDoMatrixFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class ToDoMatrixFragmentUI : AnkoComponent<ToDoMatrixFragment> {
    override fun createView(ui: AnkoContext<ToDoMatrixFragment>) = with(ui) {
        verticalLayout {
            textView("ToDoMatrixFragment")
        }
    }
}
