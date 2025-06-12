package com.example.myselfapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myselfapp.data.model.UserProfile
import com.example.myselfapp.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _userProfile = MutableLiveData<UserProfile?>()
    val userProfile: LiveData<UserProfile?> = _userProfile

    fun loadUserProfileDetail() {
        viewModelScope.launch {
            try {
                _userProfile.value = profileRepository.getUserProfile()
            } catch (e: Exception) {
                // Tangani kesalahan, misalnya log
                _userProfile.value = null // Set ke null jika ada error
            }
        }
    }
}