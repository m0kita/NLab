package com.example.domain.usecase

import com.example.domain.repository.AnalysesRepository

class GetNewsUseCase(private val repository: AnalysesRepository) {

    suspend fun execute() = repository.getNews()
}