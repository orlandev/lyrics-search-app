package com.ondev.lyricssearch.data.source.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiJSON(
    val lyrics: String
)