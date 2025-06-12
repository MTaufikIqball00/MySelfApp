package com.example.myselfapp.data.repository

import com.example.myselfapp.data.local.dao.MusicVideoDao
import com.example.myselfapp.data.local.entity.MusicVideoEntity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MusicVideoRepository @Inject constructor(
    private val musicVideoDao: MusicVideoDao,
    private val dummyDataInitializer: DummyDataInitializer
) {
    suspend fun getMusicVideos(): List<MusicVideoEntity> {
        dummyDataInitializer.insertMusicVideoData()
        return musicVideoDao.getMusicVideos()
    }

}