package com.geeks.data.repository

import com.geeks.data.dao.TaskDao
import com.geeks.data.model.toDomain
import com.geeks.domain.model.TaskEntityModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {
    override suspend fun insertTask(taskEntityModel: TaskEntityModel): Long {
        return taskDao.insertTask(taskEntityModel.fromDomain())
    }

    override suspend fun getTaskById(taskId: Int): TaskEntityModel? {
        return taskDao.getTaskById(taskId)?.toDomain()
    }

    override suspend fun deleteTask(taskId: Long) {
        taskDao.deleteTask(taskId)
    }

    override suspend fun fetchTask(): Flow<List<TaskEntityModel>> {
        return taskDao.fetchTasks().map { taskList ->
            taskList.map { it.toDomain() }
        }
    }
}