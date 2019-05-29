package com.hirauchi.todo_matrix.activity

import android.os.Bundle
import com.hirauchi.todo_matrix.Constants
import com.hirauchi.todo_matrix.R
import com.hirauchi.todo_matrix.fragment.AddToDoFragment
import com.hirauchi.todo_matrix.model.ToDo

class AddToDoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val todo = intent.getSerializableExtra(Constants.KEY_TODO) as? ToDo
        val importance = intent.getIntExtra(Constants.KEY_IMPORTANCE, Constants.DEFAULT_VALUE)
        val urgency = intent.getIntExtra(Constants.KEY_URGENCY, Constants.DEFAULT_VALUE)

        todo?.let {
            supportActionBar?.title = getString(R.string.edit_todo_title)
        }

        supportFragmentManager.beginTransaction().replace(R.id.base_container, AddToDoFragment.newInstance(todo, importance, urgency)).commit()
    }
}