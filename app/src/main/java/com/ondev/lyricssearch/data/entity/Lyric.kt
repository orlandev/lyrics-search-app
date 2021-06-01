package com.ondev.lyricssearch.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lyrics")
data class Lyric(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val lyrics: String
)