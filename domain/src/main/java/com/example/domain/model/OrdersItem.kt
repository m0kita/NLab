package com.example.domain.model

data class OrdersItem(
    val id: Int,
    val patientId: Int,
    val orderId: Int,
    val catalogId: Int,
    val price: String,
    val createdAt: String,
    val updatedAt: String,
    val patient: Patient,
    val order: Order,
    val item: Analyse,
)