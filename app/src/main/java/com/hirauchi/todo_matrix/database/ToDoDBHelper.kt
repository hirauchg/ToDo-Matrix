package com.hirauchi.todo_matrix.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class ToDoDBHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, DB_TODO, null, DB_VERSION) {

    companion object {
        const val DB_TODO = "db_todo"
        const val DB_VERSION = 1
        const val TABLE_TODO = "table_todo"
        const val CULM_ID = "id"
        const val CULM_CONTENT = "content"
        const val CULM_IMPORTANCE = "importance"
        const val CULM_URGENCY = "urgency"

        private var instance: ToDoDBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): ToDoDBHelper {
            if (instance == null) {
                instance = ToDoDBHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(TABLE_TODO, true,
            CULM_ID to INTEGER + PRIMARY_KEY + UNIQUE,
            CULM_CONTENT to TEXT,
            CULM_IMPORTANCE to INTEGER,
            CULM_URGENCY to INTEGER
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(TABLE_TODO, true)
    }
}