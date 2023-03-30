package com.example.data.repository

import com.example.data.network.ApiClient
import com.example.domain.model.Analyse
import com.example.domain.model.News
import com.example.domain.repository.AnalysesRepository

class AnalysesRepositoryImpl: AnalysesRepository {
    private val client = ApiClient.client

    override suspend fun getNews(): List<News> = client.getNews()

    override suspend fun getAnalyses(): List<Analyse> = client.getCatalog()
}