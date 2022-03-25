package com.example.android.simpsonfortniteproject.ui.login

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
class LogInViewModel @Inject constructor(
    application: Application,
    private val loginDatabase: LogInDatabaseDao,
    private val dispatcher: Dispatchers
) : AndroidViewModel(application) {

    private var userName: String = ""
    private var passCheck: Boolean = false
    //private var passWord: String = ""

    private val _gotUser = MutableLiveData<Boolean?>()
    val gotUser: LiveData<Boolean?>
        get() = _gotUser

    private var _canLogIn: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val canLogIn: LiveData<Boolean>
        get() = _canLogIn

    fun logInUser(password: String) {
        viewModelScope.launch {
            getUserFromDatabase(userName, password)
        }
    }

    fun checkValidity(): Boolean {
        _canLogIn.value = userName.isNotEmpty() && passCheck
        return _canLogIn.value == true
    }

    private suspend fun getUserFromDatabase(userName: String, password: String) {
        viewModelScope.launch(dispatcher.IO) {
            val response = loginDatabase.getUser(userName, password)
            _gotUser.postValue(response?.userName != null && response?.password != null)
            Timber.d("User is ${response?.userName}, password is ${response?.password}")
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

    fun doneLogginIn()
    {
     _gotUser.value = null
    }
}
