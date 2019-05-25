package com.hirauchi.todo_matrix.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.todo_matrix.ui.ToDoMatrixFragmentUI
import org.jetbrains.anko.AnkoContext

class ToDoMatrixFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ToDoMatrixFragmentUI().createView(AnkoContext.create(inflater.context, this, false))
    }
}
