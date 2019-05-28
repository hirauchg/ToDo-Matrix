package com.hirauchi.todo_matrix.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.hirauchi.todo_matrix.model.ToDo
import com.hirauchi.todo_matrix.ui.ToDoListAdapterUI
import org.jetbrains.anko.AnkoContext

class ToDoListAdapter(val mListener: ToDoListListener): RecyclerView.Adapter<ToDoListAdapter.ViewHolder>() {

    private val mUI = ToDoListAdapterUI()

    interface ToDoListListener {
        fun onDeleteClicked(todo: ToDo)
        fun onEditClicked(todo: ToDo)
    }

    lateinit var mToDoList: List<ToDo>

    fun setToDoList(ToDoList: List<ToDo>) {
        mToDoList = ToDoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(mUI.createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return mToDoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mToDoList.get(position)

        holder.content.text = item.content
        holder.importance.text = item.importance.toString()
        holder.urgency.text = item.urgency.toString()

        holder.delete.setOnClickListener {
            mListener.onDeleteClicked(item)
        }

        holder.edit.setOnClickListener {
            mListener.onEditClicked(item)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val content: TextView = mUI.mContent
        val importance: TextView = mUI.mImportance
        val urgency: TextView = mUI.mUrgency
        val delete: RelativeLayout = mUI.mDelete
        val edit: RelativeLayout = mUI.mEdit
    }
}
