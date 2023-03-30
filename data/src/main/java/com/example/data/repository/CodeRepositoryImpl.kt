package com.example.data.repository

import com.example.data.network.ApiClient
import com.example.domain.model.Token
import com.example.domain.repository.CodeRepository

class CodeRepositoryImpl: CodeRepository {
    private val client = ApiClient.client
    override suspend fun sendCode(email: String): String {
        val result = client.sendCode(email)
        return result.message
    }

    override suspend fun checkCode(email: String, code: String): Token {
        return client.signIn(email, code)
    }
}