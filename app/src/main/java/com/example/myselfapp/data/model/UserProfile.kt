package com.example.myselfapp.data.model

data class UserProfile(
    val id: Int,
    val photoResId: Int,
    val name: String,
    val description: String,
    val hobbies: List<String>,
    val makes: List<String>,
    val interests: List<String>,
    val dreams: List<String>
)
