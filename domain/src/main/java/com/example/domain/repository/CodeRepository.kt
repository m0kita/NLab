package com.example.domain.repository

import com.example.domain.model.Token

interface CodeRepository {
    suspend fun sendCode(email: String): String

    suspend fun checkCode(email: String, code: String): Token
}