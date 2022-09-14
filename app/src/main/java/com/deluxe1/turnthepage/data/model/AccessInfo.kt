package com.deluxe1.turnthepage.data.model

import com.squareup.moshi.Json

data class AccessInfo(
    @Json(name = "country") val country: String? = null,
    @Json(name = "viewability") val viewability: String? = null,
    @Json(name = "embeddable") val embeddable: Boolean? = null,
    @Json(name = "publicDomain") val publicDomain: Boolean? = null,
    @Json(name = "textToSpeechPermission") val textToSpeechPermission: String? = null,
    @Json(name = "epub") val epub: Epub? = Epub(),
    @Json(name = "pdf") val pdf: Pdf? = Pdf(),
    @Json(name = "webReaderLink") val webReaderLink: String? = null,
    @Json(name = "accessViewStatus") val accessViewStatus: String? = null,
    @Json(name = "quoteSharingAllowed") val quoteSharingAllowed: Boolean? = null
)