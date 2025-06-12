package com.example.myselfapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.myselfapp.util.ListStringConverter

@Entity(tableName = "profile")
@TypeConverters(ListStringConverter::class)
data class ProfileEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1,
    val photoResId: Int,
    val name: String,
    val description: String,
    val hobbies: List<String>,
    val makes: List<String>,
    val interests: List<String>,
    val dreams: List<String>
)
