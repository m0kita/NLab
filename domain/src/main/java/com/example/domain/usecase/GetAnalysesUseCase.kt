package com.example.domain.usecase

import com.example.domain.repository.AnalysesRepository

class GetAnalysesUseCase(private val repository: AnalysesRepository) {

    suspend fun execute() = repository.getAnalyses()
}