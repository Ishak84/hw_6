package com.geeks.data.di

import androidx.room.Room
import com.geeks.data.db.TaskDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), TaskDatabase::class.java, "task")
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<TaskDatabase>().taskDao()
    }
}