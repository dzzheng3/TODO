package com.example.dz.mocktodo2.feature.tasks

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ListView
import com.example.dz.mocktodo2.R
import com.example.dz.mocktodo2.data.local.Task


class TasksFragment : Fragment() {
    internal var itemListener = object : TaskItemListener {
        override fun onTaskClick(task: Task) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onCompleteTaskClick(task: Task) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onActiveTaskClick(task: Task) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
    private val taskAdapter = TasksAdapter(emptyList(), itemListener)

    companion object {
        fun newInstance() = TasksFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.tasks_fragment, container, false)
        with(view) {
            findViewById<ListView>(R.id.lv_tasks).apply {
                adapter = taskAdapter
            }
        }
        return view
    }

    class TasksAdapter(var tasks: List<Task>, var taskItemListener: TaskItemListener) : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val task = getItem(position)
            val rowView = convertView
                    ?: LayoutInflater.from(parent?.context).inflate(R.layout.task_item, parent, false)
            with(rowView.findViewById<CheckBox>(R.id.cb)) {
                isChecked = task.isComplete
                setOnClickListener {
                    if (task.isComplete) {
                        taskItemListener.onCompleteTaskClick(task)
                    } else {
                        taskItemListener.onActiveTaskClick(task)
                    }
                }
            }
            rowView.setOnClickListener { taskItemListener.onTaskClick(getItem(position)) }
            return rowView
        }

        override fun getItem(position: Int) = tasks[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getCount(): Int = tasks.size

    }

    interface TaskItemListener {
        fun onTaskClick(task: Task)
        fun onCompleteTaskClick(task: Task)
        fun onActiveTaskClick(task: Task)
    }
}
