package com.hirauchi.todo_matrix.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.hirauchi.todo_matrix.R
import com.hirauchi.todo_matrix.fragment.ToDoListFragment
import org.jetbrains.anko.startActivity
import android.content.res.Configuration
import com.hirauchi.todo_matrix.fragment.ToDoMatrixFragment

class ToDoActivity : BaseActivity() {

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
            } else {
                show(mToDoMatrixFragment)
                hide(mToDoListFragment)
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
}
