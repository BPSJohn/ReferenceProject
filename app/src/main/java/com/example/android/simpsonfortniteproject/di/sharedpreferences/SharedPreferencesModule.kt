package com.example.android.simpsonfortniteproject.di.sharedpreferences

import android.content.Context
import com.example.android.simpsonfortniteproject.network.sharedpreferences.SharedPreferencesManager
import com.example.android.simpsonfortniteproject.network.sharedpreferences.SharedPreferencesManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SharedPreferencesModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext app: Context
    ) : SharedPreferencesManager {
        return SharedPreferencesManagerImpl(app)
    }

}
