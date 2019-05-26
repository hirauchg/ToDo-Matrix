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
            cardElevation = dip(3).toFloat()
            setContentPadding(18, 14, 0, 14)

            lparams(width = matchParent, height = wrapContent) {
                horizontalMargin = dip(14)
                verticalMargin = dip(4)
            }

            linearLayout {
                verticalLayout {
                    linearLayout {
                        textView(R.string.todo_list_importance) {
                            textSize = 14F
                        }.lparams(width = 0, weight = 1F)

                        mImportance = textView {
                            textSize = 14F
                        }.lparams(width = 0, weight = 1F)

                        textView(R.string.todo_list_urgency) {
                            textSize = 14F
                        }.lparams(width = 0, weight = 1F)

                        mUrgency = textView {
                            textSize = 14F
                        }.lparams(width = 0, weight = 1F)
                    }

                    view {
                        setBackgroundColor(ContextCompat.getColor(ctx, android.R.color.darker_gray))
                    }.lparams(width = matchParent, height = 2) {
                        verticalMargin = dip(3)
                    }

                    mContent = textView {
                        textSize = 16F
                    }
                }.lparams(width = 0, height = wrapContent, weight = 8F)

                verticalLayout {
                    mDelete = relativeLayout {
                        imageView {
                            setImageResource(R.drawable.ic_delete)
                            scaleType = ImageView.ScaleType.FIT_CENTER
                        }.lparams(width = 40, height = 40) {
                            centerInParent()
                        }
                    }.lparams(width = matchParent, height = 0, weight = 1F) {
                        bottomMargin = dip(5)
                    }

                    mEdit = relativeLayout {
                        imageView {
                            setImageResource(R.drawable.ic_edit)
                            scaleType = ImageView.ScaleType.FIT_CENTER
                        }.lparams(width = 40, height = 40) {
                            centerInParent()
                        }
                    }.lparams(width = matchParent, height = 0, weight = 1F)
                }.lparams(width = 0, height = matchParent, weight = 1F)
            }
        }
    }
}