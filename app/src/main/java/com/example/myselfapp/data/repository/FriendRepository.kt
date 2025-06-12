package com.example.myselfapp.data.repository

import com.example.myselfapp.data.local.dao.FriendDao
import com.example.myselfapp.data.local.entity.FriendEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FriendRepository @Inject constructor(
    private val friendDao: FriendDao,
    private val dummyDataInitializer: DummyDataInitializer
) {
    suspend fun getFriends(): List<FriendEntity> {
        dummyDataInitializer.insertFriendListData()
        return friendDao.getFriends()
    }
}




