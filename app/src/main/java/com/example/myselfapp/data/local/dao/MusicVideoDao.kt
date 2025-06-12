package com.example.myselfapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myselfapp.data.local.entity.MusicVideoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicVideoDao {
//    @Query("SELECT * FROM music_video ORDER BY dateAdded DESC")
//    fun getAllMusicVideos(): List<MusicVideoEntity>
//
//    @Query("SELECT * FROM music_video WHERE fileType = :fileType ORDER BY title ASC")
//    fun getMusicVideosByType(fileType: String): Flow<List<MusicVideoEntity>>
//
//    @Query("SELECT * FROM music_video WHERE genre = :genre ORDER BY artist ASC, title ASC")
//    fun getMusicVideosByGenre(genre: String): Flow<List<MusicVideoEntity>>
//
//    @Query("SELECT * FROM music_video WHERE title LIKE '%' || :query || '%' OR artist LIKE '%' || :query || '%' OR album LIKE '%' || :query || '%' ORDER BY title ASC")
//    fun searchMusicVideos(query: String): Flow<List<MusicVideoEntity>>
//
//    @Query("SELECT * FROM music_video WHERE id = :id")
//    suspend fun getMusicVideoById(id: Long): MusicVideoEntity?
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertMusicVideo(musicVideo: MusicVideoEntity): Long
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertMusicVideos(musicVideos: List<MusicVideoEntity>)
//
//    @Update
//    suspend fun updateMusicVideo(musicVideo: MusicVideoEntity)
//
//    @Delete
//    suspend fun deleteMusicVideo(musicVideo: MusicVideoEntity)
//
//    @Query("DELETE FROM music_video WHERE id = :id")
//    suspend fun deleteMusicVideoById(id: Long)
//
//    @Query("DELETE FROM music_video")
//    suspend fun deleteAllMusicVideos()
//
//    @Query("SELECT COUNT(*) FROM music_video")
//    suspend fun getMusicVideosCount(): Int
//
//    @Query("SELECT COUNT(*) FROM music_video WHERE fileType = :fileType")
//    suspend fun getMusicVideosCountByType(fileType: String): Int
//
//    @Query("SELECT DISTINCT artist FROM music_video WHERE artist IS NOT NULL ORDER BY artist ASC")
//    suspend fun getAllArtists(): List<String>
//
//    @Query("SELECT DISTINCT album FROM music_video WHERE album IS NOT NULL ORDER BY album ASC")
//    suspend fun getAllAlbums(): List<String>
//
//    @Query("SELECT DISTINCT genre FROM music_video WHERE genre IS NOT NULL ORDER BY genre ASC")
//    suspend fun getAllGenres(): List<String>
//
//    @Query("SELECT SUM(duration) FROM music_video")
//    suspend fun getTotalDuration(): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMusicVideos(videos: List<MusicVideoEntity>)

    @Query("SELECT * FROM music_videos ORDER BY title ASC")
    suspend fun getMusicVideos(): List<MusicVideoEntity>

    @Query("SELECT COUNT(*) FROM music_videos") // Menambahkan fungsi hitung
    suspend fun getMusicVideoCount(): Int
}