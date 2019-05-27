package com.hirauchi.todo_matrix.ui

import android.support.v4.content.ContextCompat
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.hirauchi.todo_matrix.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ToDoListAdapterUI : AnkoComponent<ViewGroup> {

    lateinit var mContent: TextView
    lateinit var mImportance: TextView
    lateinit var mUrgency: TextView
    lateinit var mDelete: RelativeLayout
    lateinit var mEdit: RelativeLayout

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        cardView {
            cardElevation = dip(2).toFloat()
            setContentPadding(dip(8), dip(8), 0, dip(8))

            lparams(width = matchParent, height = wrapContent) {
                horizontalMargin = dip(12)
                topMargin = dip(8)
            }

            linearLayout {
                verticalLayout {
                    linearLayout {
                        textView(R.string.todo_list_importance).lparams(width = 0, weight = 1F)
                        mImportance = textView().lparams(width = 0, weight = 1F)

                        textView(R.string.todo_list_urgency).lparams(width = 0, weight = 1F)
                        mUrgency = textView().lparams(width = 0, weight = 1F)
                    }

                    view {
                        setBackgroundColor(ContextCompat.getColor(ctx, android.R.color.darker_gray))
                    }.lparams(width = matchParent, height = dip(1)) {
                        verticalMargin = dip(4)
                    }

                    mContent = textView {
                        textSize = 16F
                    }
                }.lparams(width = 0, height = wrapContent, weight = 1F)

                verticalLayout {
                    mDelete = relativeLayout {
                        imageView {
                            setImageResource(R.drawable.ic_delete)
                            scaleType = ImageView.ScaleType.FIT_CENTER
                        }.lparams(width = dip(20), height = dip(20)) {
                            centerInParent()
                        }
                    }.lparams(width = matchParent, height = 0, weight = 1F) {
                        bottomMargin = dip(10)
                    }

                    mEdit = relativeLayout {
                        imageView {
                            setImageResource(R.drawable.ic_edit)
                            scaleType = ImageView.ScaleType.FIT_CENTER
                        }.lparams(width = dip(20), height = dip(20)) {
                            centerInParent()
                        }
                    }.lparams(width = matchParent, height = 0, weight = 1F)
                }.lparams(width = dip(40), height = matchParent)
            }
        }
    }
}