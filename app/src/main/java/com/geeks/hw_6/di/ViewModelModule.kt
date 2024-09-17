package com.geeks.hw_6.di

import com.geeks.hw_6.ui.fragment.taskCreate.TaskCreateViewModel
import com.geeks.hw_6.ui.fragment.taskDetail.TaskDetailViewModel
import com.geeks.hw_6.ui.fragment.taskList.TaskListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        TaskCreateViewModel(get())
    }

    viewModel {
        TaskListViewModel(get(), get(), get())
    }

    viewModel{
        TaskDetailViewModel(get())
    }
}