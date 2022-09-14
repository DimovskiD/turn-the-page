package com.deluxe1.turnthepage.data.model

import com.squareup.moshi.Json

data class ImageLinks(
    @Json(name = "smallThumbnail") val smallThumbnail: String? = null,
    @Json(name = "thumbnail") val thumbnail: String? = null
)