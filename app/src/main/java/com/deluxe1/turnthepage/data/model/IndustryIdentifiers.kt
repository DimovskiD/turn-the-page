package com.deluxe1.turnthepage.data.model

import com.squareup.moshi.Json

data class IndustryIdentifiers(
    @Json(name = "type") val type: String? = null,
    @Json(name = "identifier") val identifier: String? = null
)