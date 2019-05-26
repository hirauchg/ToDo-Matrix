package com.hirauchi.todo_matrix.ui

import android.view.Gravity
import android.widget.*
import com.hirauchi.todo_matrix.fragment.AddToDoFragment
import org.jetbrains.anko.*

class AddToDoFragmentUI : AnkoComponent<AddToDoFragment> {

    lateinit var mImportance: SeekBar
    lateinit var mUrgency: SeekBar
    lateinit var mContent: EditText
    lateinit var mButton: Button

    override fun createView(ui: AnkoContext<AddToDoFragment>) = with(ui) {
        scrollView {
            setPadding(44, 44, 44, 44)

            verticalLayout {
                textView("重要度") {
                    textSize = 18F
                }.lparams(width = matchParent) {
                    bottomMargin = dip(15)
                }

                linearLayout {
                    for (i in 1..10) {
                        textView(i.toString()) {
                            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        }.lparams(width = 0, weight = 1F)
                    }
                }

                mImportance = seekBar {
                    max = 9
                }.lparams(width = matchParent) {
                    bottomMargin = dip(30)
                }

                textView("緊急度") {
                    textSize = 18F
                }.lparams(width = matchParent) {
                    bottomMargin = dip(15)
                }

                linearLayout {
                    for (i in 1..10) {
                        textView(i.toString()) {
                            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        }.lparams(width = 0, weight = 1F)
                    }
                }

                mUrgency = seekBar {
                    max = 9
                }.lparams(width = matchParent) {
                    bottomMargin = dip(30)
                }

                textView("内容") {
                    textSize = 18F
                }.lparams(width = matchParent) {
                    bottomMargin = dip(10)
                }

                mContent = editText().lparams(width = matchParent) {
                    bottomMargin = dip(30)
                }

                mButton = button("追加").lparams(width = wrapContent) {
                    gravity = Gravity.CENTER_HORIZONTAL
                    bottomMargin = dip(30)
                }
            }
        }
    }
}