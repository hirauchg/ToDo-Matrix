package com.hirauchi.todo_matrix.ui

import android.support.v4.content.ContextCompat
import android.widget.GridView
import com.hirauchi.todo_matrix.R
import com.hirauchi.todo_matrix.fragment.ToDoMatrixFragment
import org.jetbrains.anko.*

class ToDoMatrixFragmentUI : AnkoComponent<ToDoMatrixFragment> {

    lateinit var mGridView: GridView

    override fun createView(ui: AnkoContext<ToDoMatrixFragment>) = with(ui) {
        linearLayout {
            verticalLayout {
                textView("データを表示")
                textView("データを表示")
                textView("データを表示")
                textView("データを表示")
            }.lparams(width = 0, height = matchParent, weight = 1F)

            verticalLayout {
                linearLayout {
                    relativeLayout {
                        textView("高").lparams {
                            centerHorizontally()
                            topMargin = dip(10)
                        }
                        textView("重要度").lparams {
                            centerInParent()
                        }
                        textView("低").lparams {
                            centerHorizontally()
                            alignParentBottom()
                            bottomMargin = dip(10)
                        }
                    }.lparams(width = dip(60), height = matchParent)

                    verticalLayout {
                        backgroundColor = ContextCompat.getColor(ctx, R.color.gray)
                        mGridView = gridView {
                            numColumns = 10
                            horizontalSpacing = dip(1)
                            verticalSpacing = dip(1)
                        }.lparams(width = matchParent, height = matchParent) {
                            marginStart = dip(1)
                        }
                    }.lparams(width = 0, height = matchParent, weight = 1F)
                }.lparams(width = matchParent, height = 0, weight = 1F)

                relativeLayout {
                    textView("低").lparams {
                        leftMargin = dip(10)
                    }
                    textView("緊急度").lparams {
                        centerHorizontally()
                    }
                    textView("高").lparams {
                        alignParentEnd()
                        rightMargin = dip(10)
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(10)
                    leftMargin = dip(60)
                }
            }.lparams(width = 0, height = matchParent, weight = 2F)


        }
    }
}
