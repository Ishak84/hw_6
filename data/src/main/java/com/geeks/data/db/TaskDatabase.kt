package com.geeks.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geeks.data.dao.TaskDao
import com.geeks.data.model.TaskEntityDto

@Database(entities = [TaskEntityDto::class], version = 2)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}