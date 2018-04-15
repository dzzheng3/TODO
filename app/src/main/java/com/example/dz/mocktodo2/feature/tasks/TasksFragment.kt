package com.example.dz.mocktodo2.feature.tasks

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ListView
import com.example.dz.mocktodo2.R
import com.example.dz.mocktodo2.data.local.Task
import kotlinx.android.synthetic.main.tasks_fragment.*

class TasksFragment : Fragment(), TaskContractor.View {
    override var isActive: Boolean = false
        get() = isAdded

    override fun setLoadingIndicator(active: Boolean) {
        val root = view ?: return
        with(root.findViewById<ScrollChildSwipeRefreshLayout>(R.id.refresh_layout)) {
            post {
                isRefreshing = active
            }
        }
    }

    override fun showTasks(tasks: List<Task>) {
        listAdapter.tasks = tasks
        ll_no_task.visibility = View.INVISIBLE
        ll_all_task.visibility = View.VISIBLE
    }

    override fun showAddTask() {
        showNoTaskView()
    }

    private fun showNoTaskView() {
        ll_no_task.visibility = View.VISIBLE
        ll_all_task.visibility = View.INVISIBLE
    }

    override fun showTaskDetailsUi(taskId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTaskMarkedComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTaskMarkedActive() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showCompletedTasksCleared() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadingTasksError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoTasks() {
        showNoTaskView()
    }

    override fun showActiveFilterLabel() {
        tv_filter.text = getString(R.string.lable_active)
    }

    override fun showCompletedFilterLabel() {
        tv_filter.text = getString(R.string.lable_completed)
    }

    override fun showAllFilterLabel() {
        tv_filter.text = getString(R.string.lable_all)
    }

    override fun showNoActiveTasks() {
        showNoTaskView()
    }

    override fun showNoCompletedTasks() {
        showNoTaskView()
    }

    override fun showSuccessfullySavedMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showFilteringPopUpMenu() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override var presenter: TaskContractor.Presenter
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
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
    private val listAdapter = TasksAdapter(emptyList(), itemListener)

    companion object {
        fun newInstance() = TasksFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.tasks_fragment, container, false)
        with(view) {
            findViewById<ListView>(R.id.lv_tasks).apply {
                adapter = listAdapter
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
