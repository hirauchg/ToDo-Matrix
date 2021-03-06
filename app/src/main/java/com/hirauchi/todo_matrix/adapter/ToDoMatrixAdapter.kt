package com.hirauchi.todo_matrix.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.hirauchi.todo_matrix.R
import com.hirauchi.todo_matrix.model.MatrixData
import com.hirauchi.todo_matrix.ui.ToDoMatrixAdatperUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.backgroundColor

class ToDoMatrixAdapter(val mContext: Context?): BaseAdapter() {

    inner class ViewHolder {
        lateinit var toDoCount: TextView
    }

    lateinit var mMatrixDataList: List<MatrixData>

    fun setMatrixDataList(matrixDataList: List<MatrixData>) {
        mMatrixDataList = matrixDataList
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val matrixData = mMatrixDataList.get(position)

        val viewHolder = ViewHolder()
        var newConvertView = View(mContext)

        parent?.let {
            val ui = ToDoMatrixAdatperUI()
            newConvertView = ui.createView(AnkoContext.create(it.context, it))
            viewHolder.apply {
                toDoCount = ui.mToDoCount
            }
            newConvertView.layoutParams = AbsListView.LayoutParams((it.width / 8) - 9, (it.height / 8) - 9)
        }

        if (matrixData.toDoList.isNotEmpty()) {
            viewHolder.toDoCount.text = matrixData.toDoList.size.toString()
        }

        var category = 0
        if (matrixData.importance > 4 || matrixData.urgency > 4) category = 1
        if (matrixData.importance > 4 && matrixData.urgency > 4) category = 2
        newConvertView.backgroundColor = when (category) {
            2 -> ContextCompat.getColor(mContext!!, R.color.category_2)
            1 -> ContextCompat.getColor(mContext!!, R.color.category_1)
            else -> ContextCompat.getColor(mContext!!, R.color.category_0)
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