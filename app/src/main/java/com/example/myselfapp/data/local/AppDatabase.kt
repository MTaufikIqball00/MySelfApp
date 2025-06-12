package com.example.myselfapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myselfapp.data.local.dao.DailyActivityDao
import com.example.myselfapp.data.local.dao.FriendDao
import com.example.myselfapp.data.local.dao.GalleryDao
import com.example.myselfapp.data.local.dao.MusicVideoDao
import com.example.myselfapp.data.local.dao.ProfileDao
import com.example.myselfapp.data.local.entity.DailyActivityEntity
import com.example.myselfapp.data.local.entity.FriendEntity
import com.example.myselfapp.data.local.entity.GalleryItemEntity
import com.example.myselfapp.data.local.entity.MusicVideoEntity
import com.example.myselfapp.data.local.entity.ProfileEntity
import com.example.myselfapp.util.ListStringConverter

@Database(
    entities = [
        ProfileEntity::class,
        DailyActivityEntity::class,
        FriendEntity::class,
        GalleryItemEntity::class,
        MusicVideoEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListStringConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun dailyActivityDao(): DailyActivityDao
    abstract fun friendDao(): FriendDao
    abstract fun galleryDao(): GalleryDao
    abstract fun musicVideoDao(): MusicVideoDao
}