package com.hirauchi.todo_matrix.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.todo_matrix.adapter.ToDoMatrixAdapter
import com.hirauchi.todo_matrix.adapter.ToDoMatrixListAdapter
import com.hirauchi.todo_matrix.manager.ToDoManager
import com.hirauchi.todo_matrix.model.MatrixData
import com.hirauchi.todo_matrix.model.ToDo
import com.hirauchi.todo_matrix.ui.ToDoMatrixFragmentUI
import org.jetbrains.anko.AnkoContext

class ToDoMatrixFragment : Fragment(), ToDoMatrixAdapter.OnClickListener, ToDoMatrixListAdapter.ToDoMatrixListListener {

    lateinit var mContext: Context
    lateinit var mToDoManager: ToDoManager
    lateinit var mAdapter: ToDoMatrixListAdapter

    private val mUI = ToDoMatrixFragmentUI()
    private var mToDoList = listOf<ToDo>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mToDoManager = ToDoManager(mContext)
        mToDoList = mToDoManager.getToDoList()

        setUpList()
        setUpMatrix()
    }

    private fun setUpList() {
        mAdapter = ToDoMatrixListAdapter(mContext, this)
        mAdapter.setToDoList(arrayListOf())
        mUI.mRecyclerView.adapter = mAdapter
    }

    private fun setUpMatrix() {
        val mMatrixDataList = arrayListOf<MatrixData>()

        var importance = 8
        var urgency = 1
        for (i in 1..64) {
            val list = arrayListOf<ToDo>()
            for (todo in mToDoList) {
                if (todo.importance.equals(importance) && todo.urgency.equals(urgency)) {
                    list.add(todo)
                }
            }

            val matrixData = MatrixData(importance, urgency, list)
            mMatrixDataList.add(matrixData)

            if (i % 8 == 0) {
                importance--
                urgency -= 8
            }
            urgency++
        }

        mUI.mGridView.adapter = ToDoMatrixAdapter(context, this, mMatrixDataList)
    }

    override fun onItemClicked(matrixData: MatrixData) {
        mAdapter.setToDoList(matrixData.toDoList)
        mAdapter.notifyDataSetChanged()

        mUI.mImportance.text = matrixData.importance.toString()
        mUI.mUrgency.text = matrixData.urgency.toString()
    }

    override fun onDeleteClicked(position: Int) {

    }

    override fun onEditClicked(position: Int) {

    }
}
