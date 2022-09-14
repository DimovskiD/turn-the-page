package com.deluxe1.turnthepage.data.model

import com.squareup.moshi.Json

data class Books(
    @Json(name = "kind") val kind: String? = null,
    @Json(name = "totalItems") val totalItems: Int? = null,
    @Json(name = "items") val items: List<Book> = arrayListOf()
)