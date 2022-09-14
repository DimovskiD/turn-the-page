package com.deluxe1.turnthepage.data.model

import com.squareup.moshi.Json

data class SaleInfo(
    @Json(name = "country") val country: String? = null,
    @Json(name = "saleability") val saleability: String? = null,
    @Json(name = "isEbook") val isEbook: Boolean? = null
)