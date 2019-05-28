package com.hirauchi.todo_matrix.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hirauchi.todo_matrix.model.ToDo
import com.hirauchi.todo_matrix.ui.ToDoMatrixListAdapterUI
import org.jetbrains.anko.AnkoContext

class ToDoMatrixListAdapter(val mListener: ToDoMatrixListListener): RecyclerView.Adapter<ToDoMatrixListAdapter.ViewHolder>() {

    private val mUI = ToDoMatrixListAdapterUI()

    interface ToDoMatrixListListener {
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

        holder.delete.setOnClickListener {
            mListener.onDeleteClicked(item)
        }

        holder.edit.setOnClickListener {
            mListener.onEditClicked(item)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val content: TextView = mUI.mContent
        val delete: ImageView = mUI.mDelete
        val edit: ImageView = mUI.mEdit
    }
}
