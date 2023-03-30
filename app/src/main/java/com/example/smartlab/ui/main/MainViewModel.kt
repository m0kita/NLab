package com.example.smartlab.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(private val sharedPreferences: SharedPreferences): ViewModel() {
    private val _isFirstLaunch = MutableStateFlow(true)
    val isFirstLaunch = _isFirstLaunch.asStateFlow()
    init {
        _isFirstLaunch.value = sharedPreferences.getBoolean("BOOLEAN", true)
    }
}
