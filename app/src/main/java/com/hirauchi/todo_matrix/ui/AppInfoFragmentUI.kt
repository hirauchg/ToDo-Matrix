package com.hirauchi.todo_matrix.ui

import android.widget.ListView
import com.hirauchi.todo_matrix.fragment.AppInfoFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.listView
import org.jetbrains.anko.verticalLayout

class AppInfoFragmentUI : AnkoComponent<AppInfoFragment> {

    lateinit var mListView: ListView

    override fun createView(ui: AnkoContext<AppInfoFragment>) = with(ui) {
        verticalLayout {
            mListView = listView()
        }
    }
}