package com.example.domain.model

import com.squareup.moshi.Json

data class CodeResponse(
    @Json(name = "message") val message: String
)