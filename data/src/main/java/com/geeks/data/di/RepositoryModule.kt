package com.geeks.data.di

import org.koin.dsl.module

val repositoryModule = module {
    single<TaskRepository> { TaskRepositoryImpl(get()) }
}