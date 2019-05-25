package com.hirauchi.todo_matrix.ui

import com.hirauchi.todo_matrix.fragment.ToDoListFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class ToDoListFragmentUI : AnkoComponent<ToDoListFragment> {
    override fun createView(ui: AnkoContext<ToDoListFragment>) = with(ui) {
        verticalLayout {
            textView("ToDoListFragment")
        }
    }
}
