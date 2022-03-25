package com.example.android.simpsonfortniteproject.ui.sharedpreferences

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.android.simpsonfortniteproject.network.sharedpreferences.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedPreferencesViewModel @Inject constructor(
    application: Application,
) : AndroidViewModel(application) {



}
