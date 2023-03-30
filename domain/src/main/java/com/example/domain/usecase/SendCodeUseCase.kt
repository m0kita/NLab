package com.example.domain.usecase

import com.example.domain.repository.CodeRepository

class SendCodeUseCase(private val repository: CodeRepository) {
    suspend fun execute(email: String): String {
        return repository.sendCode(email)
    }
}