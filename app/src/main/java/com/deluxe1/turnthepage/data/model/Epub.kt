package com.deluxe1.turnthepage.data.model

import com.squareup.moshi.Json

data class Epub(
    @Json(name = "isAvailable") var isAvailable: Boolean? = null
)