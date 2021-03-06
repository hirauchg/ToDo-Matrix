package com.hirauchi.todo_matrix.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.SeekBar
import com.hirauchi.todo_matrix.Constants
import com.hirauchi.todo_matrix.R
import com.hirauchi.todo_matrix.manager.ToDoManager
import com.hirauchi.todo_matrix.model.ToDo
import com.hirauchi.todo_matrix.ui.AddToDoFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.toast

class AddToDoFragment : Fragment() {

    lateinit var mContext: Context
    lateinit var mToDoManager: ToDoManager

    private val mUI = AddToDoFragmentUI()
    private var mImportanceValue = 1
    private var mUrgencyValue = 1
    private var mToDo: ToDo? = null

    companion object {
        fun newInstance(todo: ToDo?, importance: Int, urgency: Int): AddToDoFragment = AddToDoFragment().apply {
            arguments = Bundle().apply {
                putSerializable(Constants.KEY_TODO, todo)
                putInt(Constants.KEY_IMPORTANCE, importance)
                putInt(Constants.KEY_URGENCY, urgency)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onAttach(context: Context){
        mContext = context
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mToDoManager = ToDoManager(mContext)

        arguments?.apply {
            mToDo = getSerializable(Constants.KEY_TODO) as? ToDo
            mImportanceValue = getInt(Constants.KEY_IMPORTANCE)
            mUrgencyValue = getInt(Constants.KEY_URGENCY)
        }

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        setUpView()
    }

    private fun setUpView() {
        mUI.apply {
            mImportance.progress = mImportanceValue - 1
            mImportance.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    mImportanceValue = progress + 1
                }
                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
            })

            mUrgency.progress = mUrgencyValue - 1
            mUrgency.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    mUrgencyValue = progress + 1
                }
                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
            })

            mButton.setOnClickListener {
                if (mUI.mContent.text.trim().isEmpty()) {
                    toast("内容を入力してください")
                    return@setOnClickListener
                }

                mToDo?.let {
                    mToDoManager.updateToDo(it.id, mUI.mContent.text.toString(), mImportanceValue, mUrgencyValue)
                } ?: mToDoManager.addToDo(mUI.mContent.text.toString(), mImportanceValue, mUrgencyValue)

                activity?.setResult(Activity.RESULT_OK)
                activity?.finish()
            }

            mToDo?.let {
                mImportance.progress = it.importance - 1
                mUrgency.progress = it.urgency - 1
                mContent.setText(it.content)
                mButton.setText(R.string.edit_todo_button)
            }
        }
    }
}