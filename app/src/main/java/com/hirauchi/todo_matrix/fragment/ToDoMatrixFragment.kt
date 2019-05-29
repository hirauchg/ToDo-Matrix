package com.hirauchi.todo_matrix.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.todo_matrix.R
import com.hirauchi.todo_matrix.activity.ToDoActivity
import com.hirauchi.todo_matrix.adapter.ToDoMatrixAdapter
import com.hirauchi.todo_matrix.adapter.ToDoMatrixListAdapter
import com.hirauchi.todo_matrix.manager.ToDoManager
import com.hirauchi.todo_matrix.model.MatrixData
import com.hirauchi.todo_matrix.model.ToDo
import com.hirauchi.todo_matrix.ui.ToDoMatrixFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.backgroundColor

class ToDoMatrixFragment : Fragment(), ToDoMatrixListAdapter.ToDoMatrixListListener {

    lateinit var mContext: Context
    lateinit var mToDoManager: ToDoManager
    lateinit var mListAdapter: ToDoMatrixListAdapter
    lateinit var mMatrixAdapter: ToDoMatrixAdapter

    private val mUI = ToDoMatrixFragmentUI()
    private var mMatrixDataList = arrayListOf<MatrixData>()
    private var mBeforeView: View? = null
    private var mBeforeData: MatrixData? = null

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

        setData()
        setUpList()
        setUpMatrix()
    }

    fun setData() {
        val toDoList = mToDoManager.getToDoList()

        var importance = 8
        var urgency = 1
        for (i in 1..64) {
            val list = arrayListOf<ToDo>()
            for (todo in toDoList) {
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
    }

    fun reload() {
        mMatrixDataList.clear()
        setData()

        mListAdapter.setToDoList(arrayListOf())
        mListAdapter.notifyDataSetChanged()

        mMatrixAdapter.setMatrixDataList(mMatrixDataList)
        mMatrixAdapter.notifyDataSetChanged()

        mUI.mAddButton.visibility = View.GONE
        mUI.mImportance.text = ""
        mUI.mUrgency.text = ""
    }

    private fun setUpList() {
        mListAdapter = ToDoMatrixListAdapter(this)
        mListAdapter.setToDoList(arrayListOf())
        mUI.mRecyclerView.adapter = mListAdapter

        mUI.mAddButton.setOnClickListener {
            (activity as ToDoActivity).addToDo(mBeforeData?.importance, mBeforeData?.urgency)
        }
    }

    private fun setUpMatrix() {
        mMatrixAdapter = ToDoMatrixAdapter(mContext)
        mMatrixAdapter.setMatrixDataList(mMatrixDataList)
        mUI.mGridView.adapter = mMatrixAdapter
        mUI.mGridView.setOnItemClickListener { _, view, position, _ ->
            val matrixData = mMatrixDataList.get(position)

            mListAdapter.setToDoList(matrixData.toDoList)
            mListAdapter.notifyDataSetChanged()

            mBeforeData?.let {
                var category = 0
                if (it.importance > 4 || it.urgency > 4) category = 1
                if (it.importance > 4 && it.urgency > 4) category = 2
                mBeforeView?.backgroundColor = when (category) {
                    2 -> ContextCompat.getColor(mContext, R.color.category_2)
                    1 -> ContextCompat.getColor(mContext, R.color.category_1)
                    else -> ContextCompat.getColor(mContext, R.color.category_0)
                }
            }
            mBeforeData = matrixData
            mBeforeView = view
            view.setBackgroundColor(ContextCompat.getColor(mContext, R.color.category_select))

            mUI.mImportance.text = matrixData.importance.toString()
            mUI.mUrgency.text = matrixData.urgency.toString()
            mUI.mAddButton.visibility = View.VISIBLE
        }
    }

    override fun onDeleteClicked(todo: ToDo) {
        (activity as ToDoActivity).deleteToDo(todo)
    }

    override fun onEditClicked(todo: ToDo) {
        (activity as ToDoActivity).editToDo(todo)
    }
}
