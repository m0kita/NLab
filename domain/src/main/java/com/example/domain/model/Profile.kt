package com.example.domain.model

data class Profile(
    val id: Int,
    val userId: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val gender: String,
    val createdAt: String,
    val updatedAt: String,
    val bithday: String
)