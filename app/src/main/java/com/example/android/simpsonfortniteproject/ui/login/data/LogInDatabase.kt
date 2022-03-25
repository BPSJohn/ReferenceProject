package com.example.android.simpsonfortniteproject.ui.login.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class LogInDatabase: RoomDatabase() {

    abstract val logInDatabaseDao: LogInDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: LogInDatabase? = null
    }
}
