package com.ondev.lyricssearch.data.source.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ILyricsApi {
    @GET("v1/{artist}/{title}")
    suspend fun searchLyric(
        @Path("artist") artist: String, @Path("title") lyricName: String
    ): Response<ApiJSON>
}