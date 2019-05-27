package com.hirauchi.todo_matrix.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.todo_matrix.Constants
import com.hirauchi.todo_matrix.R
import com.hirauchi.todo_matrix.activity.AddToDoActivity
import com.hirauchi.todo_matrix.adapter.ToDoListAdapter
import com.hirauchi.todo_matrix.manager.ToDoManager
import com.hirauchi.todo_matrix.model.ToDo
import com.hirauchi.todo_matrix.ui.ToDoListFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.yesButton

class ToDoListFragment : Fragment(), ToDoListAdapter.ToDoListener {

    lateinit var mContext: Context
    lateinit var mAdapter: ToDoListAdapter
    lateinit var mToDoManager: ToDoManager

    private val mUI = ToDoListFragmentUI()
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
        mAdapter = ToDoListAdapter(mContext, this)
        mUI.mRecyclerView.adapter = mAdapter

        loadTaskList()
    }

    fun loadTaskList() {
        mToDoList = mToDoManager.getToDoList()

        mAdapter.setToDoList(mToDoList)
        mAdapter.notifyDataSetChanged()
    }

    override fun onDeleteToDo(position: Int) {
        val toDo = mToDoList.get(position)

        alert {
            message = mContext.getString(R.string.todo_list_delete_message, toDo.content)
            yesButton {
                mToDoManager.deleteToDo(toDo.id)
                loadTaskList()
            }
        }.show()
    }

    override fun onEditToDo(position: Int) {
        activity?.startActivityForResult<AddToDoActivity>(Constants.REQUEST_CODE_EDIT_TODO, Constants.KEY_TODO to mToDoList.get(position))
    }
}