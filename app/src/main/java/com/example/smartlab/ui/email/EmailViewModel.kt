package com.example.smartlab.ui.email

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.CodeRepositoryImpl
import com.example.domain.usecase.SendCodeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EmailViewModel() : ViewModel() {
    private val repository = CodeRepositoryImpl()
    private val sendCodeUseCase = SendCodeUseCase(repository)
    private val _code = MutableStateFlow("")
    val code get() = _code.asStateFlow()

    suspend fun sendCode(email: String): String {
        _code.value = sendCodeUseCase.execute(email)
        return code.value
    }
}