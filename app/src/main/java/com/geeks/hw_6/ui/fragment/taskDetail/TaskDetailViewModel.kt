package com.geeks.hw_6.ui.fragment.taskDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.geeks.domain.model.TaskEntityModel
import com.geeks.domain.usescase.GetTaskUseCase
import kotlinx.coroutines.Dispatchers

class TaskDetailViewModel(
    private val getTaskUseCase: GetTaskUseCase
) : ViewModel() {

    fun getTaskById(taskId: Int): LiveData<TaskEntityModel?> = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(getTaskUseCase(taskId))
    }
}