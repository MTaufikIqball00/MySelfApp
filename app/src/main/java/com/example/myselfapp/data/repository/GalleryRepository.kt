package com.example.myselfapp.data.repository

import com.example.myselfapp.data.local.dao.GalleryDao
import com.example.myselfapp.data.local.entity.GalleryItemEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GalleryRepository @Inject constructor(
    private val galleryDao: GalleryDao,
    private val dummyDataInitializer: DummyDataInitializer
) {
    suspend fun getGalleryItems(): List<GalleryItemEntity> {
        dummyDataInitializer.insertGalleryData()
        return galleryDao.getGalleryItems()
    }
}