package com.example.dz.mocktodo2.feature.tasks

import com.example.dz.mocktodo2.data.local.Task
import com.example.dz.mocktodo2.data.resource.TasksRepository

class TasksPresenter(var tasksRepository: TasksRepository, var tasksView: TaskContractor.View) : TaskContractor.Presenter {
    private var first = true
    override fun start() {
        loadTasks(false)
    }

    override var currentFilterType: TaskFilterType = TaskFilterType.ALL_TASKS
    override fun resule(requestCode: Int, resultCode: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadTasks(forceUpdate: Boolean) {
        loadTasks(forceUpdate || first, true)
    }

    private fun loadTasks(forceUpdate: Boolean, loadUi: Boolean) {
        if (loadUi)
            tasksView.setLoadingIndicator(true)
//        if (forceUpdate)
    }

    override fun addNewTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openTaskDetail(requestTask: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun completeTask(completedTesk: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun activateTask(activeTask: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearCompleteTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}