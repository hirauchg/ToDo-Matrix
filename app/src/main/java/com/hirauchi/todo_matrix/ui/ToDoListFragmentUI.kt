package com.hirauchi.todo_matrix.ui

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hirauchi.todo_matrix.R
import com.hirauchi.todo_matrix.fragment.ToDoListFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ToDoListFragmentUI : AnkoComponent<ToDoListFragment> {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mFab: FloatingActionButton

    override fun createView(ui: AnkoContext<ToDoListFragment>) = with(ui) {
        relativeLayout {
            mRecyclerView = recyclerView {
                clipToPadding = false
                bottomPadding = dip(80)
                layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
            }.lparams(width = matchParent, height = wrapContent)

            mFab = floatingActionButton {
                imageResource = R.drawable.ic_add
            }.lparams {
                bottomMargin = dip(8)
                rightMargin = dip(16)
                alignParentBottom()
                alignParentEnd()
            }
        }
    }
}
