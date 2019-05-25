package com.hirauchi.todo_matrix.activity

import android.os.Bundle
import com.hirauchi.todo_matrix.R
import com.hirauchi.todo_matrix.fragment.AppInfoFragment

class AppInfoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.base_container, AppInfoFragment()).commit()
    }

}