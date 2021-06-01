package com.ondev.lyricssearch.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ondev.lyricssearch.data.entity.Lyric

@Dao
interface ILyricsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lyric: Lyric)

    @Update
    suspend fun update(lyric: Lyric)

    @Query("SELECT * FROM lyrics ORDER BY id DESC")
    fun getWallpapers(): LiveData<List<Lyric>>

    @Query("DELETE FROM lyrics")
    suspend fun deleteAll()

    @Query("DELETE FROM lyrics WHERE id = :id")
    suspend fun deleteByID(id: Int)
}