package com.deluxe1.turnthepage.data.model

import com.squareup.moshi.Json

data class VolumeInfo(
    @Json(name = "title") val title: String? = null,
    @Json(name = "imageLinks") val imageLinks: ImageLinks? = ImageLinks(),
)