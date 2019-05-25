package com.hirauchi.todo_matrix.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.todo_matrix.adapter.ToDoMatrixAdapter
import com.hirauchi.todo_matrix.model.MatrixData
import com.hirauchi.todo_matrix.model.ToDo
import com.hirauchi.todo_matrix.ui.ToDoMatrixFragmentUI
import org.jetbrains.anko.AnkoContext

class ToDoMatrixFragment : Fragment(), ToDoMatrixAdapter.OnClickListener {

    private val mUI = ToDoMatrixFragmentUI()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mMatrixDataList = arrayListOf<MatrixData>()

        for (i in 0..99) {
            val toDoList = listOf<ToDo>(ToDo(1, "context", 5F, 2F))
            val matrixData = MatrixData(toDoList)
            mMatrixDataList.add(matrixData)
        }

        val toDoMatrixAdapter = ToDoMatrixAdapter(context, this, mMatrixDataList)
        mUI.mGridView.adapter = toDoMatrixAdapter
    }

    override fun onItemClicked(toDoList: List<ToDo>) {

    }
}
