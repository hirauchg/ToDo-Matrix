package com.hirauchi.todo_matrix.ui

import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ToDoMatrixAdatperUI : AnkoComponent<ViewGroup> {

    lateinit var mToDoCount: TextView

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        cardView {
            cardElevation = dip(2).toFloat()
            radius = 0F

            relativeLayout {
                mToDoCount = textView().lparams {
                    centerInParent()
                }
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}