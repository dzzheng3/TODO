package com.example.dz.mocktodo2.data.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task @JvmOverloads constructor(@PrimaryKey var id: Int, var title: String, var description: String) {
    var isComplete = false
    val titleForList
        get() = if (title.isNotEmpty()) title else description
    val isActive
        get() = !isComplete
    val isEmpty
        get() = title.isEmpty() && description.isEmpty()
}