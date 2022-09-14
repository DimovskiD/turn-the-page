package com.deluxe1.turnthepage.data.model

import com.squareup.moshi.Json

data class SearchInfo (
  @Json(name ="textSnippet" ) val textSnippet : String? = null
)