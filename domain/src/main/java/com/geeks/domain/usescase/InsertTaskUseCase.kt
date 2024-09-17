package com.geeks.domain.usescase

import com.TaskRepository
import com.geeks.domain.model.TaskEntityModel

class InsertTaskUseCase {
    class InsertTaskUseCase(private val taskRepository: TaskRepository) {
        suspend operator fun invoke(task: TaskEntityModel): Long {
            return taskRepository.insertTask(task)
        }
    }
}