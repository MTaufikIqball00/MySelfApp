package com.example.myselfapp.di

import android.content.Context
import androidx.room.Room
import com.example.myselfapp.data.local.AppDatabase
import com.example.myselfapp.data.local.dao.DailyActivityDao
import com.example.myselfapp.data.local.dao.FriendDao
import com.example.myselfapp.data.local.dao.GalleryDao
import com.example.myselfapp.data.local.dao.MusicVideoDao
import com.example.myselfapp.data.local.dao.ProfileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "myselfapp_db"
        ).fallbackToDestructiveMigration() // Handle schema changes during development
            .build()
    }

    @Singleton
    @Provides
    fun provideProfileDao(database: AppDatabase): ProfileDao {
        return database.profileDao()
    }

    @Singleton
    @Provides
    fun provideDailyActivityDao(database: AppDatabase): DailyActivityDao {
        return database.dailyActivityDao()
    }

    @Singleton
    @Provides
    fun provideFriendDao(database: AppDatabase): FriendDao {
        return database.friendDao()
    }

    @Singleton
    @Provides
    fun provideGalleryDao(database: AppDatabase): GalleryDao {
        return database.galleryDao()
    }

    @Singleton
    @Provides
    fun provideMusicVideoDao(database: AppDatabase): MusicVideoDao {
        return database.musicVideoDao()
    }
}