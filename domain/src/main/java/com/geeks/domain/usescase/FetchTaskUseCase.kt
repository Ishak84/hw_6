package com.geeks.domain.usescase

import com.TaskRepository

class FetchTaskUseCase {

    class FetchTaskUseCase(private val taskRepository: TaskRepository) {
        suspend operator fun invoke() = taskRepository.fetchTask()
    }
}