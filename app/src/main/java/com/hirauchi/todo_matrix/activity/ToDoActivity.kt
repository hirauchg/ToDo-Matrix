package com.hirauchi.todo_matrix.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.hirauchi.todo_matrix.R
import com.hirauchi.todo_matrix.fragment.ToDoListFragment
import org.jetbrains.anko.startActivity
import android.content.res.Configuration
import android.view.*
import com.hirauchi.todo_matrix.Constants
import com.hirauchi.todo_matrix.fragment.ToDoMatrixFragment
import com.hirauchi.todo_matrix.manager.ToDoManager
import com.hirauchi.todo_matrix.model.ToDo
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.yesButton

class ToDoActivity : BaseActivity() {

    lateinit var mToDoManager: ToDoManager

    private val mToDoListFragment = ToDoListFragment()
    private val mToDoMatrixFragment = ToDoMatrixFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        mToDoManager = ToDoManager(this)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.base_container, mToDoListFragment)
            add(R.id.base_container, mToDoMatrixFragment)
            show(mToDoListFragment)
            hide(mToDoMatrixFragment)
            commit()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        supportFragmentManager.beginTransaction().apply {
            if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                show(mToDoListFragment)
                hide(mToDoMatrixFragment)
                supportActionBar?.show()
            } else {
                show(mToDoMatrixFragment)
                hide(mToDoListFragment)
                supportActionBar?.hide()
            }
            commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_app_info -> startActivity<AppInfoActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            reloadData()
        }
    }

    private fun reloadData() {
        mToDoListFragment.loadTaskList()
        mToDoMatrixFragment.reload()
    }

    fun addToDo(importance: Int? = null, urgency: Int? = null) {
        startActivityForResult<AddToDoActivity>(Constants.REQUEST_CODE_ADD_TODO, Constants.KEY_IMPORTANCE to importance, Constants.KEY_URGENCY to urgency)
    }

    fun editToDo(todo: ToDo) {
        startActivityForResult<AddToDoActivity>(Constants.REQUEST_CODE_EDIT_TODO, Constants.KEY_TODO to todo)
    }

    fun deleteToDo(todo: ToDo) {
        alert {
            message = getString(R.string.todo_list_delete_message, todo.content)
            yesButton {
                mToDoManager.deleteToDo(todo.id)
                reloadData()
            }
        }.show()
    }
}
