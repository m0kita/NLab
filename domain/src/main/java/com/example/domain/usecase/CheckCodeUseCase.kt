package com.example.domain.usecase

import com.example.domain.model.Token
import com.example.domain.repository.CodeRepository

class CheckCodeUseCase(private val repository: CodeRepository) {

    suspend fun execute(email: String, code: String): Token {
        return repository.checkCode(email, code)
    }
}