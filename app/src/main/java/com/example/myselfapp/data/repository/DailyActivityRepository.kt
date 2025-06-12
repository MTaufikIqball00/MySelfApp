package com.example.myselfapp.data.repository

import com.example.myselfapp.data.local.dao.DailyActivityDao
import com.example.myselfapp.data.local.entity.DailyActivityEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DailyActivityRepository @Inject constructor(
    private val dailyActivityDao: DailyActivityDao,
    private val dummyDataInitializer: DummyDataInitializer
) {
    suspend fun getDailyActivities(): List<DailyActivityEntity> {
        dummyDataInitializer.insertDailyActivityData()
        return dailyActivityDao.getDailyActivities()
    }
}