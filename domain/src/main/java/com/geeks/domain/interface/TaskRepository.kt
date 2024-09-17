package com.interface

import com.geeks.domain.model.TaskEntityModel
import java.util.concurrent.Flow

interface TaskRepository {
    suspend fun insertTask(taskEntityModel: TaskEntityModel): Long
    suspend fun getTaskById(taskId: Int): TaskEntityModel?
    suspend fun deleteTask(taskId: Long)
    suspend fun fetchTask(): Flow<List<TaskEntityModel>>
}