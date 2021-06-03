package com.ondev.lyricssearch.data.source.remote

import javax.inject.Inject

class LyricsRemoteDataSource @Inject constructor(private val lyrivsApi: ILyricsApi) :
    BaseDataSource() {
    suspend fun searchLyrics(artist: String, title: String) =
        getResult { lyrivsApi.searchLyric(artist, title) }
}
