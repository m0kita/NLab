package com.example.domain.model

data class Analyse(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val category: String,
    val time_result: String = "",
    val preparation: String,
    val bio: String,
)