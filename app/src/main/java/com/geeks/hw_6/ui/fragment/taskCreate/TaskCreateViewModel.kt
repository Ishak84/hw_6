package com.geeks.hw_6.ui.fragment.taskCreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.domain.usescase.InsertTaskUseCase
import com.geeks.hw_6.models.TaskEntityUI
import com.geeks.hw_6.models.fromDomain
import kotlinx.coroutines.launch

class TaskCreateViewModel(
    private val insertTaskUseCase: InsertTaskUseCase
) : ViewModel() {

    fun insertTask(task: TaskEntityUI) {
        viewModelScope.launch {
            val taskEntityModel =
                task.fromDomain()
            insertTaskUseCase(taskEntityModel)
        }
    }
}