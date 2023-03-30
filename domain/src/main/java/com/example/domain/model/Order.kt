package com.example.domain.model

data class Order(
    val id: Int,
    val address: String,
    val dateTime: String,
    val phone: String,
    val comment: String,
    val audioComment: String,
    val createdAt: String,
    val updatedAt: String,
)