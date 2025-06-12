package com.example.myselfapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myselfapp.data.local.entity.DailyActivityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyActivityDao {
//    @Query("SELECT COUNT(*) FROM daily_activity")
//    suspend fun getActivityCount(): Int
//
//    @Query("SELECT * FROM daily_activity")
//    fun getAllActivities(): List<DailyActivityEntity>
//
//    @Query("SELECT * FROM daily_activity WHERE id = :id")
//    suspend fun getActivityById(id: Long): DailyActivityEntity?
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertActivity(activity: DailyActivityEntity): Long
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertActivities(activities: List<DailyActivityEntity>)
//
//    @Update
//    suspend fun updateActivity(activity: DailyActivityEntity)
//
//    @Delete
//    suspend fun deleteActivity(activity: DailyActivityEntity)
//
//    @Query("DELETE FROM daily_activity WHERE id = :id")
//    suspend fun deleteActivityById(id: Long)
//
//    @Query("DELETE FROM daily_activity")
//    suspend fun deleteAllActivities()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyActivities(activities: List<DailyActivityEntity>) // Nama fungsi yang konsisten

    @Query("SELECT * FROM daily_activity ORDER BY id DESC")
    suspend fun getDailyActivities(): List<DailyActivityEntity>

    @Query("SELECT COUNT(*) FROM daily_activity") // Menambahkan fungsi hitung
    suspend fun getDailyActivityCount(): Int // Nama yang konsisten

}