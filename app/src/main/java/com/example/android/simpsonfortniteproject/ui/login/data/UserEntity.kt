package com.example.android.simpsonfortniteproject.ui.login.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login_user_list")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    var userID: Long = 0L,

    @ColumnInfo(name = "username")
    var userName: String = "",

    @ColumnInfo(name = "password")
    var password: String = ""
        )
