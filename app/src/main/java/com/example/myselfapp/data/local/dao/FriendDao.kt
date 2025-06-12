package com.example.myselfapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myselfapp.data.local.entity.FriendEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FriendDao {
//    @Query("SELECT * FROM friend ORDER BY name ASC")
//    fun getAllFriends(): List<FriendEntity>
//
//    @Query("SELECT * FROM friend WHERE id = :id")
//    suspend fun getFriendById(id: Long): FriendEntity?
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertFriend(friend: FriendEntity): Long
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertFriends(friends: List<FriendEntity>)
//
//    @Update
//    suspend fun updateFriend(friend: FriendEntity)
//
//    @Delete
//    suspend fun deleteFriend(friend: FriendEntity)
//
//    @Query("DELETE FROM friend WHERE id = :id")
//    suspend fun deleteFriendById(id: Long)
//
//    @Query("DELETE FROM friend")
//    suspend fun deleteAllFriends()
//
//    @Query("SELECT COUNT(*) FROM friend")
//    suspend fun getFriendsCount(): Int
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFriends(friends: List<FriendEntity>)

    @Query("SELECT * FROM friends ORDER BY name ASC")
    suspend fun getFriends(): List<FriendEntity>

    @Query("SELECT COUNT(*) FROM friends") // Menambahkan fungsi hitung
    suspend fun getFriendCount(): Int
}