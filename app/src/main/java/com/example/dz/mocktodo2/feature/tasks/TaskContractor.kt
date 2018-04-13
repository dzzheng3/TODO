package com.example.dz.mocktodo2.feature.tasks

import com.example.dz.mocktodo2.BaseView
import com.example.dz.mocktodo2.data.local.Task

interface TaskContractor {
    interface Presenter {
        var currentFilterType: TaskFilterType
        fun resule(requestCode: Int, resultCode: Int)
        fun loadTasks(forceUpdate: Boolean)
        fun addNewTask()
        fun openTaskDetail(requestTask: Task)
        fun completeTask(completedTesk: Task)
        fun activateTask(activeTask: Task)
        fun clearCompleteTask()
    }

    interface View : BaseView<Presenter> {
        var isActive: Boolean
        fun setLoadingIndicator(active: Boolean)
        fun showTasks(tasks: List<Task>)

        fun showAddTask()

        fun showTaskDetailsUi(taskId: String)

        fun showTaskMarkedComplete()

        fun showTaskMarkedActive()

        fun showCompletedTasksCleared()

        fun showLoadingTasksError()

        fun showNoTasks()

        fun showActiveFilterLabel()

        fun showCompletedFilterLabel()

        fun showAllFilterLabel()

        fun showNoActiveTasks()

        fun showNoCompletedTasks()

        fun showSuccessfullySavedMessage()

        fun showFilteringPopUpMenu()
    }
}