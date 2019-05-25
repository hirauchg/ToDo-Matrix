package com.hirauchi.todo_matrix.ui

import android.support.v4.content.ContextCompat
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.hirauchi.todo_matrix.R
import org.jetbrains.anko.*

class ToDoMatrixAdatperUI : AnkoComponent<ViewGroup> {

    lateinit var mContainer: RelativeLayout
    lateinit var mToDoCount: TextView

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        verticalLayout {
            mContainer = relativeLayout {
                backgroundColor = ContextCompat.getColor(ctx, R.color.white)

                mToDoCount = textView().lparams {
                    centerHorizontally()
                }
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}