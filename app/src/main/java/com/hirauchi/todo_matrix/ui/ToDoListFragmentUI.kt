package com.hirauchi.todo_matrix.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hirauchi.todo_matrix.fragment.ToDoListFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ToDoListFragmentUI : AnkoComponent<ToDoListFragment> {
    lateinit var mRecyclerView: RecyclerView

    override fun createView(ui: AnkoContext<ToDoListFragment>) = with(ui) {
        verticalLayout {
            mRecyclerView = recyclerView {
                layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
            }.lparams(width = matchParent, height = matchParent) {
                topMargin = dip(8)
                bottomMargin = dip(4)
            }
        }
    }
}
