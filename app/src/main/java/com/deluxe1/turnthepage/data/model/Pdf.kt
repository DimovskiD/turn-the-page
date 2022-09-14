package com.deluxe1.turnthepage.data.model

import com.squareup.moshi.Json

data class Pdf(
    @Json(name = "isAvailable") val isAvailable: Boolean? = null
)