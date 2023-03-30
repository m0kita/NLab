package com.example.domain.repository

import com.example.domain.model.Analyse
import com.example.domain.model.News


interface AnalysesRepository {
    suspend fun getNews(): List<News>

    suspend fun getAnalyses(): List<Analyse>
}