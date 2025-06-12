package com.example.myselfapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myselfapp.data.local.entity.GalleryItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GalleryDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGalleryItems(items: List<GalleryItemEntity>)

    @Query("SELECT * FROM gallery_items ORDER BY id DESC")
    suspend fun getGalleryItems(): List<GalleryItemEntity>

    @Query("SELECT COUNT(*) FROM gallery_items") // Menambahkan fungsi hitung
    suspend fun getGalleryItemCount(): Int

}