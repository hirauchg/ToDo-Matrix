package com.hirauchi.todo_matrix.manager

import android.content.Context
import com.hirauchi.todo_matrix.database.ToDoDBHelper
import com.hirauchi.todo_matrix.model.ToDo
import org.jetbrains.anko.db.*

class ToDoManager(ctx: Context) {

    private val mDB = ToDoDBHelper.getInstance(ctx)

    fun getToDoList() : List<ToDo> {
        lateinit var toDoList : List<ToDo>
        mDB.use {
            toDoList = select(ToDoDBHelper.TABLE_TODO).parseList(classParser())
        }
        return toDoList
    }

    fun getToDo(id: Int) : ToDo {
        lateinit var toDo : ToDo
        mDB.use {
            toDo = select(ToDoDBHelper.TABLE_TODO).whereArgs("(id = {Id})", "Id" to id).parseSingle(classParser())
        }
        return toDo
    }

    fun addToDo(content: String, importance: Int, urgency: Int) {
        mDB.use {
            insert(ToDoDBHelper.TABLE_TODO,
                ToDoDBHelper.CULM_CONTENT to content,
                ToDoDBHelper.CULM_IMPORTANCE to importance,
                ToDoDBHelper.CULM_URGENCY to urgency)
        }
    }

    fun updateToDo(id: Int, content: String, importance: Int, urgency: Int) {
        mDB.use {
            update(ToDoDBHelper.TABLE_TODO,
                ToDoDBHelper.CULM_CONTENT to content,
                ToDoDBHelper.CULM_IMPORTANCE to importance,
                ToDoDBHelper.CULM_URGENCY to urgency)
                .whereSimple(ToDoDBHelper.CULM_ID + " = ?", id.toString()).exec()
        }
    }

    fun deleteToDo(id: Int) {
        mDB.use {
            delete(ToDoDBHelper.TABLE_TODO, ToDoDBHelper.CULM_ID + " = ?", arrayOf(id.toString()))
        }
    }
}