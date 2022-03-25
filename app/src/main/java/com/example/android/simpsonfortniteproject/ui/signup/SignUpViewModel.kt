package com.example.android.simpsonfortniteproject.ui.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.simpsonfortniteproject.ui.login.data.LogInDatabaseDao
import com.example.android.simpsonfortniteproject.ui.login.data.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    application: Application,
    private val logInDatabase: LogInDatabaseDao,
    private val dispatcher: Dispatchers
) : AndroidViewModel(application) {

    private var userName: String = ""
    private var passCheck: Boolean = false

    private var _canSignUp: MutableLiveData<Boolean> = MutableLiveData(false)
    val canSignUp: LiveData<Boolean>
        get() = _canSignUp

    fun signUpUser(password: String) {
        val user = UserEntity()

        user.userName = userName
        user.password = password
        insertUser(user)
        Timber.d("New user created.")
    }

    private fun insertUser(user: UserEntity)
    {
        viewModelScope.launch(dispatcher.IO) {
            logInDatabase.insert(user)
        }
    }

    fun setUsername(name: String?) {
        if (name != null) {
            userName = name
            Timber.d("Username is $userName")
        }
    }

    fun setPassword(pass: String?) {
        passCheck = pass != null
        Timber.d("Password is $passCheck")

        checkValidity()
    }

    fun checkValidity(): Boolean {
        _canSignUp.value = userName.isNotEmpty() && passCheck
        return _canSignUp.value == true
    }

}
