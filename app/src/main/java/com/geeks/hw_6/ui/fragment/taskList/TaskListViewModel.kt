package com.geeks.hw_6.ui.fragment.taskList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.domain.usescase.DeleteTaskUseCase
import com.geeks.domain.usescase.FetchTaskUseCase
import com.geeks.domain.usescase.GetTaskUseCase
import kotlinx.coroutines.launch

class TaskListViewModel(
    private val getTaskUseCase: GetTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val fetchTaskUseCase: FetchTaskUseCase
) : ViewModel() {
    fun getTaskById(taskId: Int) {
        viewModelScope.launch {
            getTaskUseCase(taskId)
        }
    }

    fun deleteTask(taskId: Long) {
        viewModelScope.launch {
            deleteTaskUseCase(taskId)
        }
    }

    suspend fun fetchTasks() = fetchTaskUseCase()
}