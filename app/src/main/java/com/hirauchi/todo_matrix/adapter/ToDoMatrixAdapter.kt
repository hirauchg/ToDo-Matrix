package com.hirauchi.todo_matrix.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.hirauchi.todo_matrix.model.MatrixData
import com.hirauchi.todo_matrix.model.ToDo
import com.hirauchi.todo_matrix.ui.ToDoMatrixAdatperUI
import org.jetbrains.anko.AnkoContext

class ToDoMatrixAdapter(val mContext: Context?, val mListener: ToDoMatrixAdapter.OnClickListener, val mMatrixDataList: List<MatrixData>): BaseAdapter() {

    interface OnClickListener {
        fun onItemClicked(toDoList: List<ToDo>)
    }

    inner class ViewHolder {
        lateinit var container: RelativeLayout
        lateinit var toDoCount: TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val matrixData = mMatrixDataList.get(position)

        var viewHolder = ViewHolder()
        var newConvertView = View(mContext)

        parent?.let {
            if (convertView == null) {
                val ui = ToDoMatrixAdatperUI()
                newConvertView = ui.createView(AnkoContext.create(it.context, it))
                viewHolder.apply {
                    container = ui.mContainer
                    toDoCount = ui.mToDoCount
                }
                newConvertView.tag = viewHolder
            } else {
                newConvertView = convertView
                viewHolder = newConvertView.tag as ViewHolder
            }

            newConvertView.layoutParams = AbsListView.LayoutParams((it.width / 10) - 2, (it.height / 10) - 2)
        }

        viewHolder.toDoCount.text = matrixData.toDoList.size.toString()
        viewHolder.container.setOnClickListener {
            mListener.onItemClicked(matrixData.toDoList)
        }

        return newConvertView
    }

    override fun getItem(position: Int): Any {
        return mMatrixDataList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return mMatrixDataList.count()
    }
}