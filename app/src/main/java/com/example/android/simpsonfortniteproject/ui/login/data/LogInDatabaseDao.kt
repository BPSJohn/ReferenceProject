package com.example.android.simpsonfortniteproject.ui.login.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LogInDatabaseDao {

    @Insert
    suspend fun insert(user: UserEntity)

    @Query("SELECT * from login_user_list WHERE username LIKE :name AND password LIKE :password")
    suspend fun getUser(name: String, password: String): UserEntity?

}
