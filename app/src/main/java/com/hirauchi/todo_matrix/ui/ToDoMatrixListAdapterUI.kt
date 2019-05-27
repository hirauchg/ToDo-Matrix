package com.hirauchi.todo_matrix.ui

import android.text.TextUtils
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hirauchi.todo_matrix.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ToDoMatrixListAdapterUI : AnkoComponent<ViewGroup> {

    lateinit var mContent: TextView
    lateinit var mEdit: ImageView
    lateinit var mDelete: ImageView

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {

        cardView {
            cardElevation = dip(2).toFloat()

            lparams(width = matchParent, height = wrapContent) {
                horizontalMargin = dip(3)
                topMargin = dip(4)
            }

            linearLayout {
                padding = dip(8)

                mContent = textView {
                    textSize = 16F
                    singleLine = true
                    ellipsize = TextUtils.TruncateAt.END
                }.lparams(width = 0, weight = 1F)

                mEdit = imageView(R.drawable.ic_edit) {
                    scaleType = ImageView.ScaleType.FIT_CENTER
                }.lparams(width = dip(20))

                mDelete = imageView(R.drawable.ic_delete) {
                    scaleType = ImageView.ScaleType.FIT_CENTER
                }.lparams(width = dip(22)) {
                    leftMargin = dip(10)
                }
            }.lparams(width = matchParent, height = wrapContent)
        }
    }
}