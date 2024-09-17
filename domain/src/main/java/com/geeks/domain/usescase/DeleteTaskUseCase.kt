package com.geeks.domain.usescase

import com.TaskRepository

class DeleteTaskUseCase {
    class DeleteTaskUseCase(private val taskRepository: TaskRepository) {
        suspend operator fun invoke(taskId: Long) {
            taskRepository.deleteTask(taskId)
        }
    }
}