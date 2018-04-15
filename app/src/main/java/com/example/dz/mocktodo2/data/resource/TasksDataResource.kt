package com.example.dz.mocktodo2.data.resource

import com.example.dz.mocktodo2.data.local.Task

interface TasksDataResource {

    interface LoadTasksCallback {

        fun onTasksLoaded(tasks: List<Task>)

        fun onDataNotAvailable()
    }

    interface GetTaskCallback {

        fun onTaskLoaded(task: Task)

        fun onDataNotAvailable()
    }

    fun getTasks(callback: LoadTasksCallback)

    fun getTask(taskId: String, callback: GetTaskCallback)

    fun saveTask(task: Task)

    fun completeTask(task: Task)

    fun completeTask(taskId: String)

    fun activateTask(task: Task)

    fun activateTask(taskId: String)

    fun clearCompletedTasks()

    fun refreshTasks()

    fun deleteAllTasks()

    fun deleteTask(taskId: String)
}