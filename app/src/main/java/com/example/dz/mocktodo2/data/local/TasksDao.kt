package com.example.dz.mocktodo2.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface TasksDao {
    @Query("select * from tasks")
    fun getTask(): List<Task>

    @Query("select * from tasks where id = :taskID")
    fun getTaskById(taskID: String): Task

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task): Int

    @Query("update tasks set isComplete = :completed where id = :id")
    fun updateCompleted(id: Int, completed: Boolean)

    @Query("delete from tasks where id = :id")
    fun deleteTask(id: String)

    @Query("delete from tasks")
    fun deleteAllTasks()

    @Query("delete from tasks where isComplete = 1")
    fun deleteCompletedTasks(): Int
}