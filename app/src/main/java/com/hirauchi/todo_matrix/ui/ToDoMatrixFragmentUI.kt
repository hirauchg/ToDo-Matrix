package com.hirauchi.todo_matrix.ui

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.GridView
import android.widget.TextView
import com.hirauchi.todo_matrix.R
import com.hirauchi.todo_matrix.fragment.ToDoMatrixFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ToDoMatrixFragmentUI : AnkoComponent<ToDoMatrixFragment> {

    lateinit var mImportance: TextView
    lateinit var mUrgency: TextView
    lateinit var mRecyclerView: RecyclerView
    lateinit var mGridView: GridView

    override fun createView(ui: AnkoContext<ToDoMatrixFragment>) = with(ui) {
        linearLayout {
            verticalLayout {
                topPadding = dip(5)

                verticalLayout {
                    backgroundColor = ContextCompat.getColor(ctx, R.color.white)
                    elevation = dip(4).toFloat()

                    linearLayout {
                        textView(R.string.todo_matrix_importance) {
                            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        }.lparams(width = 0, weight = 1F)
                        textView(R.string.todo_matrix_urgency) {
                            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        }.lparams(width = 0, weight = 1F)
                    }.lparams(width = matchParent, height = wrapContent) {
                        bottomMargin = dip(4)
                    }

                    linearLayout {
                        mImportance = textView {
                            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        }.lparams(width = 0, weight = 1F)
                        mUrgency = textView {
                            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        }.lparams(width = 0, weight = 1F)
                    }.lparams(width = matchParent, height = wrapContent) {
                        bottomMargin = dip(6)
                    }
                }

                mRecyclerView = recyclerView {
                    clipToPadding = false
                    bottomPadding = dip(4)
                    layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                }.lparams(width = matchParent, height = matchParent)
            }.lparams(width = 0, height = matchParent, weight = 2F)

            view {
                backgroundColor = ContextCompat.getColor(ctx, R.color.black)
            }.lparams(width = dip(1), height = matchParent)

            verticalLayout {
                topPadding = dip(5)

                linearLayout {
                    relativeLayout {
                        textView(R.string.todo_matrix_high) {
                            textColor = ContextCompat.getColor(ctx, R.color.red)
                        }.lparams {
                            centerHorizontally()
                            topMargin = dip(10)
                        }
                        textView(R.string.todo_matrix_importance).lparams {
                            centerInParent()
                        }
                        textView(R.string.todo_matrix_low) {
                            textColor = ContextCompat.getColor(ctx, R.color.blue)
                        }.lparams {
                            centerHorizontally()
                            alignParentBottom()
                            bottomMargin = dip(10)
                        }
                    }.lparams(width = dip(56), height = matchParent)

                    verticalLayout {
                        mGridView = gridView {
                            clipToPadding = false
                            setPadding(dip(4), dip(4), dip(4), dip(4))
                            numColumns = 8
                            horizontalSpacing = dip(4)
                            verticalSpacing = dip(4)
                        }.lparams(width = matchParent, height = matchParent)
                    }.lparams(width = 0, height = matchParent, weight = 1F)
                }.lparams(width = matchParent, height = 0, weight = 1F)

                relativeLayout {
                    textView(R.string.todo_matrix_low) {
                        textColor = ContextCompat.getColor(ctx, R.color.blue)
                    }.lparams {
                        leftMargin = dip(10)
                    }
                    textView(R.string.todo_matrix_urgency).lparams {
                        centerHorizontally()
                    }
                    textView(R.string.todo_matrix_high) {
                        textColor = ContextCompat.getColor(ctx, R.color.red)
                    }.lparams {
                        alignParentEnd()
                        rightMargin = dip(10)
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    verticalMargin = dip(6)
                    leftMargin = dip(60)
                }
            }.lparams(width = 0, height = matchParent, weight = 3F)


        }
    }
}
