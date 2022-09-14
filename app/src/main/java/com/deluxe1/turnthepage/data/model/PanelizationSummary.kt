package com.deluxe1.turnthepage.data.model

import com.squareup.moshi.Json

data class PanelizationSummary(
    @Json(name = "containsEpubBubbles") val containsEpubBubbles: Boolean? = null,
    @Json(name = "containsImageBubbles") val containsImageBubbles: Boolean? = null
)