package com.hirauchi.todo_matrix.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.hirauchi.todo_matrix.R
import com.hirauchi.todo_matrix.fragment.ToDoListFragment
import org.jetbrains.anko.startActivity
import android.content.res.Configuration
import android.view.*
import com.hirauchi.todo_matrix.fragment.ToDoMatrixFragment
import org.jetbrains.anko.startActivityForResult

class ToDoActivity : BaseActivity() {

    private val REQUEST_CODE_ADD_TODO = 100

    private val mToDoListFragment = ToDoListFragment()
    private val mToDoMatrixFragment = ToDoMatrixFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

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
            R.id.menu_add_todo -> startActivityForResult<AddToDoActivity>(REQUEST_CODE_ADD_TODO)
            R.id.menu_app_info -> startActivity<AppInfoActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_TODO && resultCode == Activity.RESULT_OK) {
            mToDoListFragment.loadTaskList()
        }
    }
}
