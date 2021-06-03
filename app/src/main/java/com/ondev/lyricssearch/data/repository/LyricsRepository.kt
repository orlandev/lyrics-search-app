package com.ondev.lyricssearch.data.repository

import androidx.lifecycle.LiveData
import com.ondev.lyricssearch.data.entity.Lyric
import com.ondev.lyricssearch.data.source.local.ILyricsDao
import com.ondev.lyricssearch.data.source.remote.LyricsRemoteDataSource
import javax.inject.Inject

class LyricsRepository @Inject constructor(
    private val lyricsRemoteDataSource: LyricsRemoteDataSource,
    private val lyricsDao: ILyricsDao
) {

    val allLyricsSave: LiveData<List<Lyric>> = lyricsDao.getLyrics()

    suspend fun insert(lyric: Lyric) = lyricsDao.insert(lyric)

    suspend fun update(lyric: Lyric) {
        lyricsDao.update(lyric)
    }

    suspend fun deleteByID(id: Int) {
        lyricsDao.deleteByID(id)
    }

    suspend fun searchLyrics(artist: String, lyricTitle: String) =
        lyricsRemoteDataSource.searchLyrics(artist, lyricTitle)

}