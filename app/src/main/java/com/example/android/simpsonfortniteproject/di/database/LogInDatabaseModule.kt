package com.example.android.simpsonfortniteproject.di.database

import android.content.Context
import androidx.room.Room
import com.example.android.simpsonfortniteproject.ui.login.data.LogInDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LogInDatabaseModule {

    @Singleton
    @Provides
    fun provideLogInDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        LogInDatabase::class.java,
        "log_in_class_database"
    ).build()

    @Singleton
    @Provides
    fun provideLogInDatabaseDao(db: LogInDatabase) = db.logInDatabaseDao
}
