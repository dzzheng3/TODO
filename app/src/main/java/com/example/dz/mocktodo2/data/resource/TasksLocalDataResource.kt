package com.example.dz.mocktodo2.data.resource

import android.support.annotation.VisibleForTesting
import com.example.dz.mocktodo2.data.local.Task
import com.example.dz.mocktodo2.data.local.TasksDao
import com.example.dz.mocktodo2.data.util.AppExecutors

class TasksLocalDataResource private constructor(val appExcutors: AppExecutors, val taskSDao: TasksDao) : TasksDataResource {
    override fun getTasks(callback: TasksDataResource.LoadTasksCallback) {
        appExcutors.diskIO.execute {
            val tasks = taskSDao.getTask()
            appExcutors.mainThread.execute {
                if (tasks.isEmpty())
                    callback.onDataNotAvailable()
                else
                    callback.onTasksLoaded(tasks)
            }
        }
    }

    override fun getTask(taskId: String, callback: TasksDataResource.GetTaskCallback) {
        appExcutors.diskIO.execute {
            val task = taskSDao.getTaskById(taskId)
            appExcutors.mainThread.execute {
                if (task == null)
                    callback.onDataNotAvailable()
                else
                    callback.onTaskLoaded(task)
            }
        }
    }

    override fun saveTask(task: Task) {
        appExcutors.diskIO.execute { taskSDao.insertTask(task) }
    }

    override fun completeTask(task: Task) {
        appExcutors.diskIO.execute { taskSDao.updateCompleted(task.id, true) }
    }

    override fun completeTask(taskId: String) {
    }

    override fun activateTask(task: Task) {
        appExcutors.diskIO.execute { taskSDao.updateCompleted(task.id, true) }
    }

    override fun activateTask(taskId: String) {
    }

    override fun clearCompletedTasks() {
        appExcutors.diskIO.execute { taskSDao.deleteCompletedTasks() }
    }

    override fun refreshTasks() {
    }

    override fun deleteAllTasks() {
        appExcutors.diskIO.execute { taskSDao.deleteAllTasks() }
    }

    override fun deleteTask(taskId: String) {
        appExcutors.diskIO.execute { taskSDao.deleteTask(taskId) }
    }

    companion object {
        private var INSTANCE: TasksLocalDataResource? = null
        @JvmStatic
        fun getInstance(appExcutors: AppExecutors, taskSDao: TasksDao): TasksLocalDataResource {
            if (INSTANCE == null) {
                synchronized(TasksLocalDataResource::class.java) {
                    INSTANCE = TasksLocalDataResource(appExcutors, taskSDao)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }
}