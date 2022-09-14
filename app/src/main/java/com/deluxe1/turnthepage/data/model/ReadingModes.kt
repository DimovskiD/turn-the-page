package com.deluxe1.turnthepage.data.model

import com.squareup.moshi.Json

data class ReadingModes(
    @Json(name = "text") val text: Boolean? = null,
    @Json(name = "image") val image: Boolean? = null
)