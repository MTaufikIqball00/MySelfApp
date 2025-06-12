package com.example.myselfapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music_videos")
data class MusicVideoEntity(
    @PrimaryKey(autoGenerate = true)
//    val id: Long = 0,
//    val title: String,
//    val artist: String?,
//    val thumbnailUrl: String,
//    val album: String?,
//    val genre: String?,
//    val duration: Long, // in milliseconds
//    val fileType: String, // MUSIC, VIDEO
//    val filePath: String,
//    val mimeType: String,
//    val dateAdded: Long = System.currentTimeMillis()
    val id: Int = 0,
    val title: String,
    val artist: String?,
    val thumbnailUrl: String, // Thumbnail dari URL
    val contentUrl: String, // URL ke file musik/video atau YouTube
    val type: String, // "music" atau "video"
    val album: String? = null,
    val genre: String? = null,
    val duration: Long = 0, // Dalam milidetik
    val dateAdded: Long = 0, // Timestamp
    val mimeType: String? = null
)
