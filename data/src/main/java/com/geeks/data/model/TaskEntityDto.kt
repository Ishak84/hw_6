package com.geeks.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskEntityDto(
    @PrimaryKey(autoGenerate = true) val taskId: Long = 0,
    val taskName: String,
    val description: String,
    val time: Long
)
fun TaskEntityDto.toDomain() = TaskEntityModel(
    taskId = taskId,
    taskName = taskName,
    description = description,
    time = time
)

fun TaskEntityModel.fromDomain() = TaskEntityDto(
    taskId = taskId,
    taskName = taskName,
    description = description,
    time = time
)
