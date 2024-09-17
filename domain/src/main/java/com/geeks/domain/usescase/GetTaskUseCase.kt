package com.geeks.domain.usescase

import com.TaskRepository
import com.geeks.domain.model.TaskEntityModel

class GetTaskUseCase {
    class GetTaskUseCase(private val taskRepository: TaskRepository) {
        suspend operator fun invoke(taskId: Int): TaskEntityModel? {
            return taskRepository.getTaskById(taskId)
        }
    }
}