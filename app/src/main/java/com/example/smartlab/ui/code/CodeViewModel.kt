package com.example.smartlab.ui.code

import androidx.lifecycle.ViewModel
import com.example.data.repository.CodeRepositoryImpl
import com.example.domain.model.Token
import com.example.domain.usecase.CheckCodeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CodeViewModel() : ViewModel() {
    private val repository = CodeRepositoryImpl()
    private val checkCodeUseCase = CheckCodeUseCase(repository)
    private val _token = MutableStateFlow("")
    val token get() = _token.asStateFlow()
    private var result = ""

    suspend fun checkCode(email: String, code: String) {
        try {
            result = checkCodeUseCase.execute(email, code).token
        } catch (e: Exception) {
            return
        }
        if(result != "Оибка в логине или пароле.") {
            _token.value = result
        }
    }
}