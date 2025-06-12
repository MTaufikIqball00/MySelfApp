package com.example.myselfapp.data.repository

import com.example.myselfapp.data.local.dao.ProfileDao
import com.example.myselfapp.data.local.entity.ProfileEntity
import com.example.myselfapp.data.model.UserProfile
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val profileDao: ProfileDao,
    private val dummyDataInitializer: DummyDataInitializer
) {
    suspend fun getUserProfile(): UserProfile? {
        dummyDataInitializer.insertProfileData()
        val profileEntity = profileDao.getProfile()
        return profileEntity?.toUserProfile()
    }

    suspend fun getUserInterests(): List<String> {
        dummyDataInitializer.insertProfileData()
        val profileEntity = profileDao.getProfile()
        return profileEntity?.interests ?: emptyList()
    }

    suspend fun getUserHobbies(): List<String> {
        dummyDataInitializer.insertProfileData()
        return profileDao.getProfile()?.hobbies ?: emptyList()
    }

    suspend fun getUserMakes(): List<String> {
        dummyDataInitializer.insertProfileData()
        return profileDao.getProfile()?.makes ?: emptyList()
    }

    suspend fun getUserDreams(): List<String> {
        dummyDataInitializer.insertProfileData()
        return profileDao.getProfile()?.dreams ?: emptyList()
    }

    suspend fun updateUserProfile(profile: UserProfile) {
        val entity = ProfileEntity(
            id = profile.id,
            photoResId = profile.photoResId,
            name = profile.name,
            description = profile.description,
            interests = profile.interests,
            hobbies = profile.hobbies,
            makes = profile.makes,
            dreams = profile.dreams
        )
        profileDao.updateProfile(entity)
    }

}



// Mapper function
fun ProfileEntity.toUserProfile(): UserProfile {
    return UserProfile(
        id = this.id,
        photoResId = this.photoResId,
        name = this.name,
        description = this.description,
        interests = this.interests,
        hobbies = this.hobbies,
        makes = this.makes,
        dreams = this.dreams
    )
}
